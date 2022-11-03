package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AccountStatusEmailDTOTest {

    @Test
    public void testGetterAndSetter() {

        AccountStatusEmailDTO accountStatusEmailDTO = new AccountStatusEmailDTO();
        AccountStatusMailInfoDTO accountStatusMailInfoDTO = new AccountStatusMailInfoDTO();

        assertNull(accountStatusEmailDTO.getData());
        accountStatusEmailDTO.setData(accountStatusMailInfoDTO);
        assertEquals(accountStatusMailInfoDTO, accountStatusEmailDTO.getData());

        assertNull(accountStatusEmailDTO.getTemplate());
        String template = "abc";
        accountStatusEmailDTO.setTemplate(template);
        assertEquals(template, accountStatusEmailDTO.getTemplate());

        assertNull(accountStatusEmailDTO.getTo());
        String setTo = "Testing purpose";
        accountStatusEmailDTO.setTo(setTo);
        assertEquals(setTo, accountStatusEmailDTO.getTo());
    }

    /**
     * toString method testing
     */
    @Test
    public void toStringTest() {

        AccountStatusMailInfoDTO accountStatusMailInfoDTO = new AccountStatusMailInfoDTO();
        AccountStatusEmailDTO accountStatusEmailDTO = new AccountStatusEmailDTO();
        accountStatusEmailDTO.setData(accountStatusMailInfoDTO);

        String template = "abc";
        accountStatusEmailDTO.setTemplate(template);

        String setTo = "Testing purpose";
        accountStatusEmailDTO.setTo(setTo);

        assertEquals(
                "AccountStatusEmailDTO [data=AccountStatusMailInfoDTO [firstName=null, businessName=null, "
                        + "accountStatus=null, imgBaseUrl=null, fyndrBusinessPhoneNo=null, "
                        + "fyndrBusinessWebsite=null, fyndrAddress=null], to=Testing purpose, template=abc]",
                accountStatusEmailDTO.toString());
    }

    @Test
    public void testEqualAndHashCode() {

        String template = "abc";
        String setTo = "Testing purpose";

        AccountStatusMailInfoDTO accountStatusMailInfoDTO = new AccountStatusMailInfoDTO();

        AccountStatusEmailDTO accountStatusEmailDTO1 = setData(accountStatusMailInfoDTO, template, setTo);

        assertEquals(accountStatusEmailDTO1, accountStatusEmailDTO1);
        assertEquals(accountStatusEmailDTO1.hashCode(), accountStatusEmailDTO1.hashCode());

        assertNotEquals(accountStatusEmailDTO1, new Object());

        AccountStatusEmailDTO accountStatusEmailDTO2 = setData(accountStatusMailInfoDTO, template, setTo);
        assertEquals(accountStatusEmailDTO1, accountStatusEmailDTO2);
        assertEquals(accountStatusEmailDTO1.hashCode(), accountStatusEmailDTO2.hashCode());

        accountStatusEmailDTO2 = setData(null, template + " ", setTo);
        assertNotEquals(accountStatusEmailDTO1, accountStatusEmailDTO2);
        assertNotEquals(accountStatusEmailDTO1.hashCode(), accountStatusEmailDTO2.hashCode());

        accountStatusEmailDTO2 = setData(accountStatusMailInfoDTO, template + " ", setTo);
        assertNotEquals(accountStatusEmailDTO1, accountStatusEmailDTO2);
        assertNotEquals(accountStatusEmailDTO1.hashCode(), accountStatusEmailDTO2.hashCode());

        accountStatusEmailDTO2 = setData(accountStatusMailInfoDTO, template, setTo + " ");
        assertNotEquals(accountStatusEmailDTO1, accountStatusEmailDTO2);
        assertNotEquals(accountStatusEmailDTO1.hashCode(), accountStatusEmailDTO2.hashCode());

        accountStatusEmailDTO1 = new AccountStatusEmailDTO();
        accountStatusEmailDTO2 = new AccountStatusEmailDTO();

        assertEquals(accountStatusEmailDTO1, accountStatusEmailDTO2);
        assertEquals(accountStatusEmailDTO1.hashCode(), accountStatusEmailDTO2.hashCode());
    }

    private AccountStatusEmailDTO setData(AccountStatusMailInfoDTO accountStatusMailInfoDTO, String template,
            String setTo) {

        AccountStatusEmailDTO accountStatusEmailDTO = new AccountStatusEmailDTO();
        accountStatusEmailDTO.setData(accountStatusMailInfoDTO);
        accountStatusEmailDTO.setTemplate(template);
        accountStatusEmailDTO.setTo(setTo);
        return accountStatusEmailDTO;
    }
}
