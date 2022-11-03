package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;

public class AccountStatusMailInfoDTOTest {

    @Test
    public void testGetterAndSetter() {
        AccountStatusMailInfoDTO accountStatusMailInfoDTO = new AccountStatusMailInfoDTO();

        assertNull(accountStatusMailInfoDTO.getAccountStatus());
        accountStatusMailInfoDTO.setAccountStatus(AccountStatus.ACTIVE);
        assertEquals(AccountStatus.ACTIVE, accountStatusMailInfoDTO.getAccountStatus());

        assertNull(accountStatusMailInfoDTO.getBusinessName());
        String businessName = "Fyndr";
        accountStatusMailInfoDTO.setBusinessName(businessName);
        assertEquals(businessName, accountStatusMailInfoDTO.getBusinessName());

        assertNull(accountStatusMailInfoDTO.getFirstName());
        String firstName = "Test";
        accountStatusMailInfoDTO.setFirstName(firstName);
        assertEquals(firstName, accountStatusMailInfoDTO.getFirstName());

        assertNull(accountStatusMailInfoDTO.getFyndrBusinessWebsite());
        String bizWebsite = "test.com";
        accountStatusMailInfoDTO.setFyndrBusinessWebsite(bizWebsite);
        assertEquals(bizWebsite, accountStatusMailInfoDTO.getFyndrBusinessWebsite());

        assertNull(accountStatusMailInfoDTO.getFyndrAddress());
        String address = "Samta colony Raipur";
        accountStatusMailInfoDTO.setFyndrAddress(address);
        assertEquals(address, accountStatusMailInfoDTO.getFyndrAddress());

        assertNull(accountStatusMailInfoDTO.getFyndrBusinessPhoneNo());
        String phoneNo = "9999999999";
        accountStatusMailInfoDTO.setFyndrBusinessPhoneNo(phoneNo);
        assertEquals(phoneNo, accountStatusMailInfoDTO.getFyndrBusinessPhoneNo());

        assertNull(accountStatusMailInfoDTO.getImgBaseUrl());
        String imgBaseUrl = "https://fyndr.img.com";
        accountStatusMailInfoDTO.setImgBaseUrl(imgBaseUrl);
        assertEquals(imgBaseUrl, accountStatusMailInfoDTO.getImgBaseUrl());
    }

    @Test
    public void testToString() {

        AccountStatusMailInfoDTO accountStatusMailInfoDTO = new AccountStatusMailInfoDTO();

        accountStatusMailInfoDTO.setAccountStatus(AccountStatus.ACTIVE);

        String businessName = "Fyndr";
        accountStatusMailInfoDTO.setBusinessName(businessName);

        String firstName = "Test";
        accountStatusMailInfoDTO.setFirstName(firstName);

        String bizWebsite = "test.com";
        accountStatusMailInfoDTO.setFyndrBusinessWebsite(bizWebsite);

        String address = "Samta colony Raipur";
        accountStatusMailInfoDTO.setFyndrAddress(address);

        String phoneNo = "9999999999";
        accountStatusMailInfoDTO.setFyndrBusinessPhoneNo(phoneNo);

        String imgBaseUrl = "https://fyndr.img.com";
        accountStatusMailInfoDTO.setImgBaseUrl(imgBaseUrl);

        assertEquals(
                "AccountStatusMailInfoDTO [firstName=Test, businessName=Fyndr, "
                        + "accountStatus=ACTIVE, imgBaseUrl=https://fyndr.img.com, fyndrBusinessPhoneNo=9999999999, "
                        + "fyndrBusinessWebsite=test.com, fyndrAddress=Samta colony Raipur]",
                accountStatusMailInfoDTO.toString());
    }

    @Test
    public void testEqualAndHashCode() {

        AccountStatus accountStatus = AccountStatus.ACTIVE;
        String businessName = "Fyndr";
        String firstName = "Test";
        String bizWebsite = "test.com";
        String address = "Samta colony Raipur";
        String phoneNo = "9999999999";
        String imgBaseUrl = "https://fyndr.img.com";

        AccountStatusMailInfoDTO accountStatusMailInfoDTO1 = setData(accountStatus, businessName, firstName, bizWebsite,
                address, phoneNo, imgBaseUrl);

        assertEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO1);
        assertEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO1.hashCode());

        assertNotEquals(accountStatusMailInfoDTO1, new Object());

        AccountStatusMailInfoDTO accountStatusMailInfoDTO2 = setData(accountStatus, businessName, firstName, bizWebsite,
                address, phoneNo, imgBaseUrl);

        assertEquals(accountStatusMailInfoDTO2, accountStatusMailInfoDTO2);
        assertEquals(accountStatusMailInfoDTO2.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO2 = setData(accountStatus.INACTIVE, businessName, firstName, bizWebsite, address,
                phoneNo, imgBaseUrl);
        assertNotEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertNotEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO2 = setData(accountStatus, businessName + " ", firstName, bizWebsite, address, phoneNo,
                imgBaseUrl);
        assertNotEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertNotEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO2 = setData(accountStatus, businessName, firstName + " ", bizWebsite, address, phoneNo,
                imgBaseUrl);
        assertNotEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertNotEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO2 = setData(accountStatus, businessName, firstName, bizWebsite + " ", address, phoneNo,
                imgBaseUrl);
        assertNotEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertNotEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO2 = setData(accountStatus, businessName, firstName, bizWebsite, address + " ", phoneNo,
                imgBaseUrl);
        assertNotEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertNotEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO2 = setData(accountStatus, businessName, firstName, bizWebsite, address, phoneNo + " ",
                imgBaseUrl);
        assertNotEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertNotEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO2 = setData(accountStatus, businessName, firstName, bizWebsite, address, phoneNo,
                imgBaseUrl + " ");
        assertNotEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertNotEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());

        accountStatusMailInfoDTO1 = new AccountStatusMailInfoDTO();
        accountStatusMailInfoDTO2 = new AccountStatusMailInfoDTO();

        assertEquals(accountStatusMailInfoDTO1, accountStatusMailInfoDTO2);
        assertEquals(accountStatusMailInfoDTO1.hashCode(), accountStatusMailInfoDTO2.hashCode());
    }

    private AccountStatusMailInfoDTO setData(AccountStatus accountStatus, String businessName, String firstName,
            String bizWebsite, String address, String phoneNo, String imgBaseUrl) {

        AccountStatusMailInfoDTO accountStatusMailInfoDTO = new AccountStatusMailInfoDTO();
        accountStatusMailInfoDTO.setAccountStatus(accountStatus);
        accountStatusMailInfoDTO.setBusinessName(businessName);
        accountStatusMailInfoDTO.setFirstName(firstName);
        accountStatusMailInfoDTO.setFyndrBusinessWebsite(bizWebsite);
        accountStatusMailInfoDTO.setFyndrAddress(address);
        accountStatusMailInfoDTO.setFyndrBusinessPhoneNo(phoneNo);
        accountStatusMailInfoDTO.setImgBaseUrl(imgBaseUrl);
        return accountStatusMailInfoDTO;
    }
}
