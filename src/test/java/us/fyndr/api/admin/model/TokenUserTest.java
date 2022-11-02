package us.fyndr.api.admin.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TokenUserTest {

    @Test
    public void testGetterAndSetter() {

        TokenUser tokenUser = new TokenUser();
        
        assertNull(tokenUser.getEmail());
        String email = "email@gmail.com";
        tokenUser.setEmail(email);
        assertEquals(email, tokenUser.getEmail());
        
        assertEquals(0, tokenUser.getObjId());
        Long objId = 5L;
        tokenUser.setObjId(objId);
        assertEquals(objId, tokenUser.getObjId());
        
        assertNull(tokenUser.getBizName());
        String bizName = "bizName";
        tokenUser.setBizName(bizName);
        assertEquals(bizName, tokenUser.getBizName());
        
        assertNull(tokenUser.getUserEntity());
        String userEntity = "userEntity";
        tokenUser.setUserEntity(userEntity);
        assertEquals(userEntity, tokenUser.getUserEntity());
        
        assertNull(tokenUser.getUserRole());
        String userRole = "userRole";
        tokenUser.setUserRole(userRole);
        assertEquals(userRole, tokenUser.getUserRole());
        
        assertEquals(0, tokenUser.getGeneratedBy());
        Long generatedBy = 1L;
        tokenUser.setGeneratedBy(generatedBy);
        assertEquals(generatedBy, tokenUser.getGeneratedBy());
        
    }
    
    @Test
    public void testToString() {
        TokenUser tokenUser = new TokenUser();

        String email = "email@gmail.com";
        tokenUser.setEmail(email);
        
        Long objId = 5L;
        tokenUser.setObjId(objId);
        
        String bizName = "bizName";
        tokenUser.setBizName(bizName);
        
        String userEntity = "userEntity";
        tokenUser.setUserEntity(userEntity);
        
        String userRole = "userRole";
        tokenUser.setUserRole(userRole);
        
        Long generatedBy = 1L;
        tokenUser.setGeneratedBy(generatedBy);
        
        assertEquals("TokenUser [objId=5, email=email@gmail.com, bizName=bizName, userEntity=userEntity, userRole=userRole, generatedBy="
               + "1]", 
                tokenUser.toString());
    }
    
    @Test
    public void testEqualsAndHashcode() {
        
        String email = "email@gmail.com";
        Long objId = 5L;
        String bizName = "bizName";
        String userEntity = "userEntity";
        String userRole = "userRole";
        Long generatedBy = 1L;
        
        TokenUser tokenUser1 = buildTokenUser(objId, email, bizName, userRole, userEntity, generatedBy);
        
        assertEquals(tokenUser1, tokenUser1);
        assertEquals(tokenUser1.hashCode(), tokenUser1.hashCode());
        
        assertNotEquals(tokenUser1, new Object());
        
        TokenUser tokenUser2 = buildTokenUser(objId, email, bizName, userRole, userEntity, generatedBy);
        assertEquals(tokenUser1, tokenUser2);
        assertEquals(tokenUser1.hashCode(), tokenUser2.hashCode());
        
        tokenUser2 = buildTokenUser(2L, email, bizName, userRole, userEntity, generatedBy);
        assertNotEquals(tokenUser1, tokenUser2);
        assertNotEquals(tokenUser1.hashCode(), tokenUser2.hashCode());
        
        tokenUser2 = buildTokenUser(objId, email+" ", bizName, userRole, userEntity, generatedBy);
        assertNotEquals(tokenUser1, tokenUser2);
        assertNotEquals(tokenUser1.hashCode(), tokenUser2.hashCode());
        
        tokenUser2 = buildTokenUser(objId, email, bizName+" ", userRole, userEntity, generatedBy);
        assertNotEquals(tokenUser1, tokenUser2);
        assertNotEquals(tokenUser1.hashCode(), tokenUser2.hashCode());
        
        tokenUser2 = buildTokenUser(objId, email, bizName, userRole+" ", userEntity, generatedBy);
        assertNotEquals(tokenUser1, tokenUser2);
        assertNotEquals(tokenUser1.hashCode(), tokenUser2.hashCode());
        
        tokenUser2 = buildTokenUser(objId, email, bizName, userRole, userEntity+" ", generatedBy);
        assertNotEquals(tokenUser1, tokenUser2);
        assertNotEquals(tokenUser1.hashCode(), tokenUser2.hashCode());
        
        tokenUser2 = buildTokenUser(objId, email, bizName, userRole, userEntity, 2L);
        assertNotEquals(tokenUser1, tokenUser2);
        assertNotEquals(tokenUser1.hashCode(), tokenUser2.hashCode());
    }
    
    private TokenUser buildTokenUser(Long objId, String email, String bizName, String userRole, String userEntity, Long generatedBy) {
        TokenUser tokenUser = new TokenUser();
        
        tokenUser.setObjId(objId);
        tokenUser.setEmail(email);
        tokenUser.setBizName(bizName);
        tokenUser.setUserEntity(userEntity);
        tokenUser.setUserRole(userRole);
        tokenUser.setGeneratedBy(generatedBy);
        
        return tokenUser;
    }

}
