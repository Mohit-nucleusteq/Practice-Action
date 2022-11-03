/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.exception.UserNotFoundException;
import us.fyndr.api.admin.repository.IndividualRepository;
import us.fyndr.api.admin.util.ErrorConstants;

/**
 * This service validates fields related to user.
 * */
@Service
public class UserValidatorService {

    /***
     * individualRepository.
     */
    @Autowired
    private IndividualRepository individualRepository;

    /**
     * validateMasqueradeAdminAsUser will perform all validations for masqueradeAdminAsUser.
     * @param objId
     * @throws UserNotFoundException
     */
    public void validateMasqueradeAdminAsUser(final Long objId)
            throws UserNotFoundException {

        validateUserObjId(objId);

    }

    /**
     * validateUserEmail will perform validation for checking objId present in individual repository.
     * @param objId
     * @throws UserNotFoundException
     */
    public void validateUserObjId(final Long objId)
            throws UserNotFoundException {

        Optional<Individual> individual = individualRepository
                .findById(objId);

        if (!individual.isPresent()) {
            throw new UserNotFoundException(
                    String.format(ErrorConstants.OBJECT_ID_NOT_FOUND, objId));
        }
    }
}
