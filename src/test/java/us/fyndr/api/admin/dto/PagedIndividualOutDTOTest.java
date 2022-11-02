package us.fyndr.api.admin.dto;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;

public class PagedIndividualOutDTOTest {

    @Test
    public void testGetterAndSetter() {
        PagedIndividualOutDTO pagedIndividualOutDTO = new PagedIndividualOutDTO();

        assertFalse(pagedIndividualOutDTO.isLast());

        assertNull(pagedIndividualOutDTO.getUsers());
        List<IndividualOutDTO> individualsList = new ArrayList<>();
        pagedIndividualOutDTO.setUsers(individualsList);
        assertEquals(individualsList, pagedIndividualOutDTO.getUsers());

        Long count = 67L;
        pagedIndividualOutDTO.setCount(count);
        assertEquals(count, pagedIndividualOutDTO.getCount());
    }

    @Test
    public void testToString() {
        PagedIndividualOutDTO pagedIndividualOutDTO = new PagedIndividualOutDTO();

        IndividualOutDTO individualOutDTO = new IndividualOutDTO();
        AddressOutDTO addressOutDTO = new AddressOutDTO();

        individualOutDTO.setAddress(addressOutDTO);

        String businessName = "Fruits selling";
        individualOutDTO.setBusinessName(businessName);

        Instant createDt = Instant.now();
        individualOutDTO.setCreateDt(createDt);

        String email = "abc@gmail.com";
        individualOutDTO.setEmail(email);

        boolean isBusiness = true;
        individualOutDTO.setIsBusiness(isBusiness);

        String name = "TestUser";
        individualOutDTO.setName(name);

        Long objId = 5L;
        individualOutDTO.setObjId(objId);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();

        individualOutDTO.setPhone(phoneNumberOutDTO);

        AccountStatus status = AccountStatus.ACTIVE;
        individualOutDTO.setStatus(status);

        String webSite = "abc.com";
        individualOutDTO.setWebsite(webSite);

        List<IndividualOutDTO> individualsList = new ArrayList<IndividualOutDTO>();
        individualsList.add(individualOutDTO);
        pagedIndividualOutDTO.setUsers(individualsList);

        boolean last = true;
        pagedIndividualOutDTO.setLast(last);

        Long count = 67L;
        pagedIndividualOutDTO.setCount(count);

        assertEquals(
                "PagedIndividualOutDTO [last=true, users=[IndividualOutDTO [objId=5, address=AddressOutDTO [addressLine1=null, "
                        + "addressLine2=null, state=null, country=null, postalCode=null, city=null], createDt=" + createDt + ", name=TestUser, "
                        + "status=ACTIVE, isBusiness=true, businessName=Fruits selling, website=abc.com, phone=PhoneNumberOutDTO "
                        + "[countryCode=null, phoneNumber=null], email=abc@gmail.com]], count=67]",
                pagedIndividualOutDTO.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        boolean last = true;

        IndividualOutDTO individualOutDTO = new IndividualOutDTO();
        AddressOutDTO addressOutDTO = new AddressOutDTO();
        individualOutDTO.setAddress(addressOutDTO);
        individualOutDTO.setBusinessName("Test business");
        individualOutDTO.setCreateDt(Instant.now());
        individualOutDTO.setEmail("Test@gmail.com");
        individualOutDTO.setIsBusiness(false);
        individualOutDTO.setName("Test User");
        individualOutDTO.setObjId(5L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();

        individualOutDTO.setPhone(phoneNumberOutDTO);
        individualOutDTO.setStatus(AccountStatus.ACTIVE);
        individualOutDTO.setWebsite("abc.com");

        List<IndividualOutDTO> individualsList = new ArrayList<IndividualOutDTO>();

        Long count = 67L;

        PagedIndividualOutDTO pagedIndividualOutDTO1 = setData(last, individualsList, count);
        assertEquals(pagedIndividualOutDTO1, pagedIndividualOutDTO1);
        assertEquals(pagedIndividualOutDTO1.hashCode(), pagedIndividualOutDTO1.hashCode());

        assertNotEquals(pagedIndividualOutDTO1, new Object());

        PagedIndividualOutDTO pagedIndividualOutDTO2 = setData(last, individualsList, count);
        assertEquals(pagedIndividualOutDTO1, pagedIndividualOutDTO2);
        assertEquals(pagedIndividualOutDTO1.hashCode(), pagedIndividualOutDTO2.hashCode());

        pagedIndividualOutDTO2 = setData(false, individualsList, count);
        assertNotEquals(pagedIndividualOutDTO1, pagedIndividualOutDTO2);
        assertNotEquals(pagedIndividualOutDTO1.hashCode(), pagedIndividualOutDTO2.hashCode());

        List<IndividualOutDTO> individualsList1 = new ArrayList<IndividualOutDTO>();
        individualsList1.add(individualOutDTO);

        pagedIndividualOutDTO2 = setData(last, individualsList1, count);

        assertNotEquals(pagedIndividualOutDTO1, pagedIndividualOutDTO2);
        assertNotEquals(pagedIndividualOutDTO1.hashCode(), pagedIndividualOutDTO2.hashCode());

        pagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        pagedIndividualOutDTO2 = new PagedIndividualOutDTO();

        assertEquals(pagedIndividualOutDTO1, pagedIndividualOutDTO2);
        assertEquals(pagedIndividualOutDTO1.hashCode(), pagedIndividualOutDTO2.hashCode());
    }

    private PagedIndividualOutDTO setData(boolean last, List<IndividualOutDTO> individualsList, Long count) {
        PagedIndividualOutDTO pagedIndividualOutDTO = new PagedIndividualOutDTO();

        pagedIndividualOutDTO.setLast(last);
        pagedIndividualOutDTO.setUsers(individualsList);
        pagedIndividualOutDTO.setCount(count);

        return pagedIndividualOutDTO;
    }
}
