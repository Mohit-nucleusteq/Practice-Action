package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class UserStatisticsOutDTOTest {

    @Test
    public void testGetterAndSetter() {
        UserStatisticsOutDTO userStatisticsOutDTO = new UserStatisticsOutDTO();

        assertNull(userStatisticsOutDTO.getUser());
        Long user = 20l;
        userStatisticsOutDTO.setUser(user);
        assertEquals(user, userStatisticsOutDTO.getUser());

        assertNull(userStatisticsOutDTO.getMerchant());
        Long merchant = 25l;
        userStatisticsOutDTO.setMerchant(merchant);
        assertEquals(merchant, userStatisticsOutDTO.getMerchant());

        assertNull(userStatisticsOutDTO.getCustomer());
        Long customer = 30l;
        userStatisticsOutDTO.setCustomer(customer);
        assertEquals(customer, userStatisticsOutDTO.getCustomer());

    }

    @Test
    public void testToString() {
        UserStatisticsOutDTO userStatisticsOutDTO = new UserStatisticsOutDTO();

        Long user = 20l;
        userStatisticsOutDTO.setUser(user);

        Long merchant = 12l;
        userStatisticsOutDTO.setMerchant(merchant);

        Long customer = 20l;
        userStatisticsOutDTO.setCustomer(customer);
        assertEquals("UserStatisticsOutDTO [user=20, customer=20, merchant=12]", userStatisticsOutDTO.toString());

    }

    @Test
    public void testEqualsAndHashcode() {

        Long user = 12l;
        Long customer = 15l;
        Long merchant = 18l;

        UserStatisticsOutDTO userStatisticsOutDTO1 = buildUserStatisticsOutDTO(user, customer, merchant);

        assertEquals(userStatisticsOutDTO1, userStatisticsOutDTO1);
        assertEquals(userStatisticsOutDTO1.hashCode(), userStatisticsOutDTO1.hashCode());
        assertNotEquals(userStatisticsOutDTO1, new Object());

        UserStatisticsOutDTO userStatisticsOutDTO2 = buildUserStatisticsOutDTO(user, customer, merchant);
        assertEquals(userStatisticsOutDTO1, userStatisticsOutDTO2);
        assertEquals(userStatisticsOutDTO1.hashCode(), userStatisticsOutDTO2.hashCode());

        userStatisticsOutDTO2 = buildUserStatisticsOutDTO(21l, customer, merchant);
        assertNotEquals(userStatisticsOutDTO1, userStatisticsOutDTO2);
        assertNotEquals(userStatisticsOutDTO1.hashCode(), userStatisticsOutDTO2.hashCode());

        userStatisticsOutDTO2 = buildUserStatisticsOutDTO(user, 12l, merchant);
        assertNotEquals(userStatisticsOutDTO1, userStatisticsOutDTO2);
        assertNotEquals(userStatisticsOutDTO1.hashCode(), userStatisticsOutDTO2.hashCode());

        userStatisticsOutDTO2 = buildUserStatisticsOutDTO(user, customer, 17l);
        assertNotEquals(userStatisticsOutDTO1, userStatisticsOutDTO2);
        assertNotEquals(userStatisticsOutDTO1.hashCode(), userStatisticsOutDTO2.hashCode());

        userStatisticsOutDTO1 = new UserStatisticsOutDTO();
        userStatisticsOutDTO2 = new UserStatisticsOutDTO();
        assertEquals(userStatisticsOutDTO1, userStatisticsOutDTO2);
        assertEquals(userStatisticsOutDTO1.hashCode(), userStatisticsOutDTO2.hashCode());

    }

    private UserStatisticsOutDTO buildUserStatisticsOutDTO(Long user, Long customer, Long merchant) {
        UserStatisticsOutDTO userStatisticsOutDTO = new UserStatisticsOutDTO();

        userStatisticsOutDTO.setCustomer(customer);
        userStatisticsOutDTO.setMerchant(merchant);
        userStatisticsOutDTO.setUser(user);

        return userStatisticsOutDTO;
    }
}