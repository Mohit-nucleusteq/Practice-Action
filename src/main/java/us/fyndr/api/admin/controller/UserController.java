/**
*
 */
package us.fyndr.api.admin.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.SearchInDTO;
import us.fyndr.api.admin.dto.UpdateStatusInDTO;
import us.fyndr.api.admin.dto.UpdateStatusOutDTO;
import us.fyndr.api.admin.exception.TokenServiceException;
import us.fyndr.api.admin.exception.UnauthorizedException;
import us.fyndr.api.admin.exception.UserBadRequestException;
import us.fyndr.api.admin.exception.UserNotFoundException;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.service.SearchService;
import us.fyndr.api.admin.service.TokenService;
import us.fyndr.api.admin.service.UserService;
import us.fyndr.api.admin.service.UserValidatorService;
import us.fyndr.api.admin.util.URLConstants;

/**
 * @author saksh
 *
 */
@RestController
@RequestMapping(URLConstants.USER_URL)
public class UserController {

    /**
     * The UserService object.
     */
    @Autowired
    private UserService userService;

    /**
     * The searchService object.
     */
    @Autowired
    private SearchService searchService;

    /**
     * The TokenService object.
     */
    @Autowired
    private TokenService tokenService;

    /**
     * The userValidatorService object.
     */
    @Autowired
    private UserValidatorService userValidatorService;

    /**
     * The logger variable to log information or errors related to UserController
     * class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * @param searchInDTO has 2 searching criteria: userStatus and userType
     * @param pgStart     variable defines pagination should start from which page
     * @param pgSize      variable defines total fields to be displayed on one page
     *                    based on the string matches with database fields
     * @return ResponseOutDTO returns success message with PagedIndividualOutDTO
     *         object form
     */
    @PostMapping("/search")
    public ResponseOutDTO searchIndividualUsers(@RequestBody @Valid final SearchInDTO searchInDTO,
            @RequestParam(name = "pgStart", required = false, defaultValue = "1") final String pgStart,
            @RequestParam(name = "pgSize", required = false, defaultValue = "10") final String pgSize) {

        int pageStart = Integer.parseInt(pgStart);
        pageStart = pageStart <= 0 ? 1 : pageStart;

        LOGGER.info("received to search users: {}", searchInDTO.toString());

        return new ResponseOutDTO(true,
                searchService.searchIndividualUsers(pageStart - 1, Integer.parseInt(pgSize), searchInDTO));

    }

    /**
     * @param updateStatusInDto class contains userId and accountStatus of the User
     * @param headers           contains all the request headers
     * @param objId             is the unique id of a user present in our database
     * @throws UserNotFoundException   throws exception when user is not found
     * @throws UserBadRequestException throws exception when user is missing the
     *                                 required information/header in the request
     * @return updateSatusOutDTO class contains the details after updating
     *         successfully accountStatus of the user
     */
    @PutMapping(path = "/status/{objId}")
    public UpdateStatusOutDTO updateStatus(@RequestBody final UpdateStatusInDTO updateStatusInDto,
            @PathVariable("objId") final Long objId, @RequestHeader final Map<String, String> headers)
            throws UserNotFoundException, UserBadRequestException {
        LOGGER.info("Updating status {} of the user : {}", updateStatusInDto.getAccountStatus(), objId);
        return userService.updateStatus(updateStatusInDto, objId, headers.get("authorization"));
    }

    /**
     * @param objId   user objid which needs to be masqueraded
     * @param headers contains request headers which contain admin auth token
     * @return TokenResponseOutDTO class returns access token along with its expiry
     *         and user details
     * @throws UnauthorizedException
     * @throws UserNotFoundException
     * @throws TokenServiceException
     */
    @GetMapping("/masquerade/{objId}")
    public FetchUserTokenResponse masqueradeAdminAsUser(@PathVariable("objId") final Long objId,
            @RequestHeader final Map<String, String> headers)
            throws UnauthorizedException, UserNotFoundException, TokenServiceException {

        LOGGER.info("Masquerading user {}", objId);
        userValidatorService.validateMasqueradeAdminAsUser(objId);
        return tokenService.masqueradeUser(objId, headers.get("authorization"));
    }
}
