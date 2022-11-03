package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.exception.UserNotFoundException;
import us.fyndr.api.admin.repository.IndividualRepository;
import us.fyndr.api.admin.util.ErrorConstants;

public class UserValidatorServiceTest {

   @Mock
   IndividualRepository individualRepository;
   
   @InjectMocks
   UserValidatorService userValidatorService;
     
   
   @BeforeEach
   public void setUp() {
     
     MockitoAnnotations.initMocks(this);
   }
   
   @Test
   public void testValidateMasqueradeAdminAsUser() throws UserNotFoundException {
       
       Long objId = 5L;

       Individual individual = new Individual();
       
       when(individualRepository.findById(objId)).thenReturn(Optional.of(individual));
              
       assertDoesNotThrow(()-> userValidatorService.validateMasqueradeAdminAsUser(objId));

   }
   
   
   @Test
   public void testThrowErrorValidateMasqueradeAdminAsUser() throws UserNotFoundException {
       
       Long objId = 5L;

       try {
           userValidatorService.validateMasqueradeAdminAsUser(objId);
       } catch (UserNotFoundException userNotFoundException) {
           assertEquals( String.format(ErrorConstants.OBJECT_ID_NOT_FOUND, objId), userNotFoundException.getMessage());
       }
   }

}
