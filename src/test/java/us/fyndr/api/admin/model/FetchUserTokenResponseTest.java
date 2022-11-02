package us.fyndr.api.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class FetchUserTokenResponseTest {

    @Test
    public void testGetterAndSetter() {
        FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();
        
        assertNull(fetchUserTokenResponse.getAccessCode());
        String accessCode = "accessCode";
        fetchUserTokenResponse.setAccessCode(accessCode);
        assertEquals(accessCode, fetchUserTokenResponse.getAccessCode());
        
        assertNull(fetchUserTokenResponse.getAccessCodeExpiry());
        String accessCodeExpiry = "accessCodeExpiry";
        fetchUserTokenResponse.setAccessCodeExpiry(accessCodeExpiry);
        assertEquals(accessCodeExpiry, fetchUserTokenResponse.getAccessCodeExpiry());
        
        assertNull(fetchUserTokenResponse.getTokenUser());
        
        TokenUser tokenUser = new TokenUser();
        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("userEntity");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);

        fetchUserTokenResponse.setTokenUser(tokenUser);

        assertEquals(tokenUser, fetchUserTokenResponse.getTokenUser());
        
    }
    
    @Test
    public void testToString() {
        FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();
        
        String accessCode = "accessCode";
        fetchUserTokenResponse.setAccessCode(accessCode);
        
        String accessCodeExpiry = "accessCodeExpiry";
        fetchUserTokenResponse.setAccessCodeExpiry(accessCodeExpiry);
        
        TokenUser tokenUser = new TokenUser();
        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("userEntity");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);
        
        fetchUserTokenResponse.setTokenUser(tokenUser);
        
        assertEquals("FetchUserTokenResponse [accessCode=accessCode, accessCodeExpiry=accessCodeExpiry, tokenUser="+tokenUser+"]", 
                fetchUserTokenResponse.toString());
    }
    
    @Test
    public void testEqualsAndHashcode() {
        String accessCode = "accessCode";
        String accessCodeExpiry = "accessCodeExpiry";
        
        TokenUser tokenUser = new TokenUser();
        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("userEntity");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);
        
        FetchUserTokenResponse fetchUserTokenResponse1 = buildFetchUserTokenResponse(accessCode, accessCodeExpiry, tokenUser);
        
        assertEquals(fetchUserTokenResponse1, fetchUserTokenResponse1);
        assertEquals(fetchUserTokenResponse1.hashCode(), fetchUserTokenResponse1.hashCode());
        
        assertNotEquals(fetchUserTokenResponse1, new Object());
        
        FetchUserTokenResponse fetchUserTokenResponse2 = buildFetchUserTokenResponse(accessCode, accessCodeExpiry, tokenUser);
        assertEquals(fetchUserTokenResponse1, fetchUserTokenResponse2);
        assertEquals(fetchUserTokenResponse1.hashCode(), fetchUserTokenResponse2.hashCode());
        
        fetchUserTokenResponse2 = buildFetchUserTokenResponse(accessCode + " ", accessCodeExpiry, tokenUser);
        assertNotEquals(fetchUserTokenResponse1, fetchUserTokenResponse2);
        assertNotEquals(fetchUserTokenResponse1.hashCode(), fetchUserTokenResponse2.hashCode());
        
        fetchUserTokenResponse2 = buildFetchUserTokenResponse(accessCode, accessCodeExpiry+ " ", tokenUser);
        assertNotEquals(fetchUserTokenResponse1, fetchUserTokenResponse2);
        assertNotEquals(fetchUserTokenResponse1.hashCode(), fetchUserTokenResponse2.hashCode());
        
        TokenUser tokenUser2 = new TokenUser();
        tokenUser2.setEmail("email");
        tokenUser2.setObjId(1);
        tokenUser2.setBizName("bizName");
        tokenUser2.setUserEntity("userEntity");
        tokenUser2.setUserRole("userRole");
        tokenUser2.setGeneratedBy(3);
        
        fetchUserTokenResponse2 = buildFetchUserTokenResponse(accessCode, accessCodeExpiry, tokenUser2);
        assertNotEquals(fetchUserTokenResponse1, fetchUserTokenResponse2);
        assertNotEquals(fetchUserTokenResponse1.hashCode(), fetchUserTokenResponse2.hashCode());
        
        fetchUserTokenResponse1 = new FetchUserTokenResponse();
        fetchUserTokenResponse2 = new FetchUserTokenResponse();
        assertEquals(fetchUserTokenResponse1, fetchUserTokenResponse2);
        assertEquals(fetchUserTokenResponse1.hashCode(), fetchUserTokenResponse2.hashCode());
    }
    
    private FetchUserTokenResponse buildFetchUserTokenResponse(String accessCode, String accessCodeExpiry, TokenUser tokenUser) {
        FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();
        
        fetchUserTokenResponse.setAccessCode(accessCode);
        fetchUserTokenResponse.setAccessCodeExpiry(accessCodeExpiry);
        fetchUserTokenResponse.setTokenUser(tokenUser);
        
        return fetchUserTokenResponse;
    }
}

