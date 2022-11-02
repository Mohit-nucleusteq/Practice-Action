package us.fyndr.api.admin.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    @Test
    public void testGetterAndSetter() {

        Instant timestamp = Instant.parse("2022-09-03T10:37:30.00Z");
        String status = "status";
        String message = "message";
        String details = "details";
       

        ErrorResponse errorResponse = new ErrorResponse(timestamp, status, message, details);
        
        Instant timeStamp1 = Instant.parse("2022-09-03T10:37:30.00Z");
        errorResponse.setTimestamp(timeStamp1);
        assertEquals(timeStamp1, errorResponse.getTimestamp());
        
        String status1 = "status1";
        errorResponse.setStatus(status1);
        assertEquals(status1, errorResponse.getStatus());
        
        String message1 = "message1";
        errorResponse.setMessage(message1);
        assertEquals(message1, errorResponse.getMessage());
        
        String details1 = "details1";
        errorResponse.setDetails(details1);
        assertEquals(details1, errorResponse.getDetails());
        
        List<String> errorMessages = new ArrayList<String>();
        ErrorResponse errorResponse2 = new ErrorResponse(timestamp, status, errorMessages, details);
        
        errorMessages.add("errorMessages");
        errorResponse2.setErrorMessages(errorMessages);
        assertEquals(errorMessages, errorResponse2.getErrorMessages());
        
    }
    
    @Test
    public void testToString() {
        
        Instant timestamp = Instant.parse("2022-09-03T10:37:30.00Z");
        String status = "status";
        String message = "message";
        String details = "details";
        
        ErrorResponse errorResponse = new ErrorResponse(timestamp, status, message, details);
        
        assertEquals("ErrorResponse [timestamp=2022-09-03T10:37:30Z, status=status, message=message, details=details, errorMessages=null]",
                errorResponse.toString());
        
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("errorMessages");
        ErrorResponse errorResponse2 = new ErrorResponse(timestamp, status, errorMessages, details);
        
        assertEquals("ErrorResponse [timestamp=2022-09-03T10:37:30Z, status=status, message=null, details=details, errorMessages=[errorMessages]]",
                errorResponse2.toString());
        
    }
    
    @Test
    public void testEqualsAndHashcode() {
        
        Instant timestamp = Instant.parse("2022-09-03T10:37:30.00Z");
        String status = "status";
        String message = "message";
        String details = "details";
        
        ErrorResponse errorResponse1 = new ErrorResponse(timestamp, status, message, details);

        assertEquals(errorResponse1, errorResponse1);
        assertEquals(errorResponse1.hashCode(), errorResponse1.hashCode());
        
        assertNotEquals(errorResponse1, new Object());
        
        ErrorResponse errorResponse2 = new ErrorResponse(timestamp, status, message, details);
        assertEquals(errorResponse1, errorResponse2);
        assertEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
        
        Instant timestamp1 = Instant.parse("2023-09-03T10:37:30.00Z");
        
        errorResponse2 = new ErrorResponse(timestamp1, status, message, details);
        assertNotEquals(errorResponse1, errorResponse2);
        assertNotEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
        
        errorResponse2 = new ErrorResponse(timestamp1, status+ " ", message, details);
        assertNotEquals(errorResponse1, errorResponse2);
        assertNotEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
        
        errorResponse2 = new ErrorResponse(timestamp1, status, message+" ", details);
        assertNotEquals(errorResponse1, errorResponse2);
        assertNotEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
        
        errorResponse2 = new ErrorResponse(timestamp1, status, message, details+" ");
        assertNotEquals(errorResponse1, errorResponse2);
        assertNotEquals(errorResponse1.hashCode(), errorResponse2.hashCode());
        
        List<String> errorMessages = new ArrayList<String>();
        errorMessages.add("errorMessages");
        
        ErrorResponse errorResponse3 = new ErrorResponse(timestamp, status, errorMessages, details);
        assertEquals(errorResponse3, errorResponse3);
        assertEquals(errorResponse3.hashCode(), errorResponse3.hashCode());
        
        assertNotEquals(errorResponse3, new Object());
        
        ErrorResponse errorResponse4 = new ErrorResponse(timestamp, status, errorMessages, details);
        assertEquals(errorResponse3, errorResponse4);
        assertEquals(errorResponse3.hashCode(), errorResponse4.hashCode());
        
        List<String> errorMessages2 = new ArrayList<String>();
        errorMessages2.add("errorMessages 2");
        errorResponse4 = new ErrorResponse(timestamp, status, errorMessages2, details);
        assertNotEquals(errorResponse3, errorResponse4);
        assertNotEquals(errorResponse3.hashCode(), errorResponse4.hashCode());

    }

}
