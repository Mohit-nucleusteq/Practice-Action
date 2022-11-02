package us.fyndr.api.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Business;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dto.AccountStatusEmailDTO;
import us.fyndr.api.admin.dto.AccountStatusMailInfoDTO;
import us.fyndr.api.admin.dto.UpdateStatusInDTO;
import us.fyndr.api.admin.dto.UpdateStatusOutDTO;
import us.fyndr.api.admin.exception.UserBadRequestException;
import us.fyndr.api.admin.exception.UserNotFoundException;
import us.fyndr.api.admin.graph.service.GraphHelper;
import us.fyndr.api.admin.graph.service.GraphStrategy;
import us.fyndr.api.admin.repository.BusinessRepository;
import us.fyndr.api.admin.repository.UserRepository;
import us.fyndr.api.admin.util.AdminConstants;

/**
 * UserService class contains methods for Admin application.
 *
 */
@Service
public class UserService {

    /**
     * The UserRepository object.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The BusinessRepository object.
     */
    @Autowired
    private BusinessRepository businessRepository;

    /**
     * The web bucket name.
     */
    @Value("${notification.image.base.url}")
    private String webBucketName;

    /**
     * The TopicArn to send messages in SNS.
     */
    @Value("${sns.topic.arn}")
    private String topicArn;

    /**
     * The SNSService object.
     */
    @Autowired
    private SNSService snsService;

    /**
     * The GraphClientService object.
     */
    @Autowired
    private GraphClientService graphClientService;

    /**
     * The Gson object.
     */
    private static final Gson GSON = new Gson();

    /**
     * The GraphStrategy object.
     */
    @Autowired
    private GraphStrategy graphStrategy;

    /**
     * updateStatus is a method for updating the account status of user either
     * individual user or business user.
     *
     * @param updateStatusInDto class contains userId and accountStatus of the user
     * @param authToken         stores authorization header value
     * @param objId             is the unique id of a user present in our database
     * @return updateStatusOutDto class contains the details after updating
     *         successfully accountStatus of a user.
     * @throws UserNotFoundException   throws exception when the user is not found
     *                                 in our system
     * @throws UserBadRequestException throws exception when user is missing the
     *                                 required information/header in the request
     */
    public UpdateStatusOutDTO updateStatus(final UpdateStatusInDTO updateStatusInDto, final Long objId,
            final String authToken) throws UserNotFoundException, UserBadRequestException {

        Optional<Individual> optionalIndividual = userRepository.findById(objId);

        if (!optionalIndividual.isPresent()) {
            throw new UserNotFoundException(AdminConstants.USER_NOT_FOUND + " " + objId);
        }

        Individual individual = optionalIndividual.get();

        if (!individual.isBusiness() && AccountStatus.INACTIVE.equals(updateStatusInDto.getAccountStatus())) {
            throw new UserBadRequestException(AdminConstants.INVALID_INDIVIDUAL_STATUS);
        }

        String currentAccountStatus = individual.getAccountStatus().name();
        String newAccountStatus = updateStatusInDto.getAccountStatus().name();

        individual.setAccountStatus(updateStatusInDto.getAccountStatus());
        userRepository.save(individual);

        UpdateStatusOutDTO updateStatusOutDto = new UpdateStatusOutDTO();
        updateStatusOutDto.setAccountStatus(individual.getAccountStatus());
        updateStatusOutDto.setSuccess(true);
        updateStatusOutDto.setMessage(AdminConstants.USER_UPDATED_SUCCESSFULLY);

        notifyAndGraphUpdateOnStatusChange(currentAccountStatus, newAccountStatus, individual, authToken);

        return updateStatusOutDto;
    }

    /**
     * notifyAndGraphUpdateOnStatusChange method will check the currentAccountStatus
     * and newAccountStatus.
     *
     * @param currentAccountStatus has the current accountStatus stored in our
     *                             database
     * @param newAccountStatus     has the new accountStatus value which admin want
     *                             to update
     * @param individual           class contains the information of a user
     * @param authToken            stores authorization header value
     * @throws UserNotFoundException   throws exception when the user is not found
     *                                 in our system
     * @throws UserBadRequestException throws exception when user is missing the
     *                                 required information/header in the request
     */
    private void notifyAndGraphUpdateOnStatusChange(final String currentAccountStatus, final String newAccountStatus,
            final Individual individual, final String authToken) throws UserNotFoundException, UserBadRequestException {

        if (!currentAccountStatus.equals(newAccountStatus)) {
            sendSNSpushEmail(individual);
            updateGraphProfile(individual, authToken);
        }
    }

    /**
     * setAccountStatusMailInfoDTO is a method to setting details in
     * AccountStatusMailInfoDTO for sending mail.
     *
     * @param individual class contains the user details present in our database.
     * @return accountStatusMailInfoDTO class contains the details of a user
     *         required for sending mail
     * @throws UserNotFoundException   throws exception for not found request
     * @throws UserBadRequestException throws exception for bad request
     */
    public AccountStatusMailInfoDTO setAccountStatusMailInfoDTO(final Individual individual)
            throws UserNotFoundException, UserBadRequestException {

        AccountStatusMailInfoDTO accountStatusMailInfoDTO = new AccountStatusMailInfoDTO();
        accountStatusMailInfoDTO.setAccountStatus(individual.getAccountStatus());
        accountStatusMailInfoDTO.setFirstName(individual.getFirstName());

        if (individual.isBusiness()) {
            Optional<Business> optionalBusiness = businessRepository.findById(individual.getBusinessId());
            Business business = optionalBusiness.get();
            accountStatusMailInfoDTO.setBusinessName(business.getBusinessName());
        }

        accountStatusMailInfoDTO.setFyndrBusinessPhoneNo(AdminConstants.FYNDR_PHONE_NO);
        accountStatusMailInfoDTO.setFyndrAddress(AdminConstants.FYNDR_ADDRESS);
        accountStatusMailInfoDTO.setFyndrBusinessWebsite(AdminConstants.FYNDR_WEBSITE);
        accountStatusMailInfoDTO.setImgBaseUrl(webBucketName);

        return accountStatusMailInfoDTO;
    }

    /**
     * sendSNSpushEmail is a method to set the details for sending mail in
     * AccountStatusEmailDTO and pubTopicSingleMessage calling.
     *
     * @param individual class contains registered user details of users
     * @throws UserNotFoundException   throws exception for not found request
     * @throws UserBadRequestException throws exception for bad request
     */
    private void sendSNSpushEmail(final Individual individual) throws UserNotFoundException, UserBadRequestException {

        AccountStatusEmailDTO accountStatusEmailDTO = new AccountStatusEmailDTO();
        accountStatusEmailDTO.setTo(individual.getEmail());
        accountStatusEmailDTO.setData(setAccountStatusMailInfoDTO(individual));
        accountStatusEmailDTO.setTemplate(AdminConstants.ACCOUNT_STATUS_TEMPLATE);

        snsService.publishMessage(GSON.toJson(accountStatusEmailDTO), AdminConstants.EMAIL_NOTIFICATION, topicArn);
    }

    /**
     * updateGraphProfile is a method to update the details of user in graph api.
     *
     * @param individual class contains registered user details of users
     * @param authToken  used for validation
     */
    public void updateGraphProfile(final Individual individual, final String authToken) {

        GraphHelper graphHelper = graphStrategy.getStrategy(individual);
        String payloadForGraph = graphHelper.getGraphPayload(individual);

        graphClientService.sendPatchRequest(payloadForGraph, authToken);
    }
}
