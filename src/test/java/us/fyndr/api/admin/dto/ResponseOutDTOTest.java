package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ResponseOutDTOTest {

    @Test
    public void testGetterAndSetter() {

        boolean success = true;
        Object data = new Object();

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(success, data);

        responseOutDTO.setSuccess(success);
        assertEquals(success, responseOutDTO.isSuccess());

        responseOutDTO.setData(data);
        assertEquals(data, responseOutDTO.getData());

    }
    
    @Test
    public void testToString() {
        
        boolean success = true;
        Object data = new Object();
        
        ResponseOutDTO responseOutDTO = new ResponseOutDTO(success, data);
        
        assertEquals("ResponseOutDTO [success=true, data="+data+"]",
                responseOutDTO.toString());

    }

    @Test
    public void testEqualsAndHashcode() {
        
        boolean success = true;
        Object data = new Object();
        
        ResponseOutDTO responseOutDTO1 = new ResponseOutDTO(success, data);

        assertEquals(responseOutDTO1, responseOutDTO1);
        assertEquals(responseOutDTO1.hashCode(), responseOutDTO1.hashCode());
        
        assertNotEquals(responseOutDTO1, new Object());
        
        assertNotEquals(responseOutDTO1, null);
        
        ResponseOutDTO responseOutDTO2 = new ResponseOutDTO(success, data);
        assertEquals(responseOutDTO1, responseOutDTO2);
        assertEquals(responseOutDTO1.hashCode(), responseOutDTO2.hashCode());

        boolean success1 = false;
        responseOutDTO2 = new ResponseOutDTO(success1, data);
        assertNotEquals(responseOutDTO1, responseOutDTO2);
        assertNotEquals(responseOutDTO1.hashCode(), responseOutDTO2.hashCode());

        Object data1 = new Object();
        responseOutDTO2 = new ResponseOutDTO(success, data1);
        assertNotEquals(responseOutDTO1, responseOutDTO2);
        assertNotEquals(responseOutDTO1.hashCode(), responseOutDTO2.hashCode());

    }
}
