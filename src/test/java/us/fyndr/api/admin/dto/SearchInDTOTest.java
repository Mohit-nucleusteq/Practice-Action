package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.UserType;

public class SearchInDTOTest {

    @Test
    public void testGetterAndSetter() {
        SearchInDTO searchInDTO = new SearchInDTO();

        assertNull(searchInDTO.getUserStatus());
        List<AccountStatus> userStatusList = new ArrayList<>();
        searchInDTO.setUserStatus(userStatusList);
        assertEquals(userStatusList, searchInDTO.getUserStatus());

        assertNull(searchInDTO.getUserType());
        List<UserType> userTypeList = new ArrayList<>();
        searchInDTO.setUserType(userTypeList);
        assertEquals(userTypeList, searchInDTO.getUserType());

        assertNull(searchInDTO.getText());
        String text = "Text";
        searchInDTO.setText(text);
        assertEquals(text, searchInDTO.getText());

        assertNotNull(searchInDTO.getCountry());
        String country = "country";
        searchInDTO.setCountry(country);
        assertEquals(country, searchInDTO.getCountry());
    }

    @Test
    public void testToString() {
        SearchInDTO searchInDTO = new SearchInDTO();

        AccountStatus accountStatus = AccountStatus.ACTIVE;
        List<AccountStatus> userStatusList = new ArrayList<>();
        userStatusList.add(accountStatus);
        searchInDTO.setUserStatus(userStatusList);

        UserType userTypes = UserType.BUSINESS;
        List<UserType> userTypeList = new ArrayList<>();
        userTypeList.add(userTypes);
        searchInDTO.setUserType(userTypeList);

        String text = "Text";
        searchInDTO.setText(text);

        String country = "Country";
        searchInDTO.setCountry(country);

        assertEquals("SearchInDTO [userStatus=[ACTIVE], userType=[BUSINESS], text=Text, country=Country]",
                searchInDTO.toString());
    }

    @Test
    public void testHashCodeAndEqual() {
        List<AccountStatus> userStatusList = new ArrayList<>();
        List<UserType> userTypeList = new ArrayList<>();
        String text = "Text";
        String country = "Country";

        SearchInDTO searchInDTO1 = setData(userStatusList, userTypeList, text, country);

        assertEquals(searchInDTO1, searchInDTO1);
        assertEquals(searchInDTO1.hashCode(), searchInDTO1.hashCode());

        assertNotEquals(searchInDTO1, new Object());

        SearchInDTO searchInDTO2 = setData(userStatusList, userTypeList, text, country);
        assertEquals(searchInDTO1, searchInDTO2);
        assertEquals(searchInDTO1.hashCode(), searchInDTO2.hashCode());

        List<AccountStatus> userStatusList1 = new ArrayList<>();
        userStatusList1.add(AccountStatus.ACTIVE);

        searchInDTO2 = setData(userStatusList1, userTypeList, text, country);
        assertNotEquals(searchInDTO1, searchInDTO2);
        assertNotEquals(searchInDTO1.hashCode(), searchInDTO2.hashCode());

        List<UserType> userTypeList1 = new ArrayList<>();
        userTypeList1.add(UserType.BUSINESS);

        searchInDTO2 = setData(userStatusList, userTypeList1, text, country);
        assertNotEquals(searchInDTO1, searchInDTO2);
        assertNotEquals(searchInDTO1.hashCode(), searchInDTO2.hashCode());

        searchInDTO2 = setData(userStatusList, userTypeList, text + " ", country);
        assertNotEquals(searchInDTO1, searchInDTO2);
        assertNotEquals(searchInDTO1.hashCode(), searchInDTO2.hashCode());

        searchInDTO2 = setData(userStatusList, userTypeList, text, country + " ");
        assertNotEquals(searchInDTO1, searchInDTO2);
        assertNotEquals(searchInDTO1.hashCode(), searchInDTO2.hashCode());

        searchInDTO1 = new SearchInDTO();
        searchInDTO2 = new SearchInDTO();

        assertEquals(searchInDTO1, searchInDTO2);
        assertEquals(searchInDTO1.hashCode(), searchInDTO2.hashCode());
    }

    private SearchInDTO setData(List<AccountStatus> userStatus, List<UserType> userType, String text, String country) {
        SearchInDTO searchInDTO = new SearchInDTO();

        searchInDTO.setUserStatus(userStatus);
        searchInDTO.setUserType(userType);
        searchInDTO.setText(text);
        searchInDTO.setCountry(country);

        return searchInDTO;
    }
}
