/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class FetchUserTokenRequestTest {

    @Test
    public void testGetterAndSetter() {

        FetchUserTokenRequest fetchUserTokenRequest = new FetchUserTokenRequest("email@gmail.com");
        
        String email = "email@gmail.com";
        fetchUserTokenRequest.setEmail(email);
        assertEquals(email, fetchUserTokenRequest.getEmail());
        
        
    }

    @Test
    public void testToString() {
        
        FetchUserTokenRequest fetchUserTokenRequest = new FetchUserTokenRequest("email@gmail.com");
        
        String email = "email@gmail.com";
        fetchUserTokenRequest.setEmail(email);

        assertEquals("FetchUserTokenRequest [email=email@gmail.com]", fetchUserTokenRequest.toString());
    }

    @Test
    public void testEqualsAndHashcode() {

        FetchUserTokenRequest fetchUserTokenRequest1 = new FetchUserTokenRequest("email@gmail.com");

        assertEquals(fetchUserTokenRequest1, fetchUserTokenRequest1);
        assertEquals(fetchUserTokenRequest1.hashCode(), fetchUserTokenRequest1.hashCode());

        FetchUserTokenRequest fetchUserTokenRequest2 = new FetchUserTokenRequest("email@gmail.com");
        
        assertEquals(fetchUserTokenRequest1, fetchUserTokenRequest2);
        assertEquals(fetchUserTokenRequest1.hashCode(), fetchUserTokenRequest2.hashCode());

        assertNotEquals(fetchUserTokenRequest2, new Object());

        String email = "email@gmail.com";

        fetchUserTokenRequest1.setEmail(email);

        fetchUserTokenRequest2.setEmail(email);

        assertEquals(fetchUserTokenRequest1, fetchUserTokenRequest2);
        assertEquals(fetchUserTokenRequest1.hashCode(), fetchUserTokenRequest2.hashCode());

        String email2 = "email2@gmail.com";
        
        fetchUserTokenRequest2.setEmail(email2);

        assertNotEquals(fetchUserTokenRequest1, fetchUserTokenRequest2);
        assertNotEquals(fetchUserTokenRequest1.hashCode(), fetchUserTokenRequest2.hashCode());

    }

}