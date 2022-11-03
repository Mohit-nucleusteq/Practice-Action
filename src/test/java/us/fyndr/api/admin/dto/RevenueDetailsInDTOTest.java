package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class RevenueDetailsInDTOTest {

    @Test
    public void testGetterAndSetter() {
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        assertNull(revenueDetailsInDTO.getBusinessName());
        String businessName = "Business Name";
        revenueDetailsInDTO.setBusinessName(businessName);
        assertEquals(businessName, revenueDetailsInDTO.getBusinessName());
        
        assertNull(revenueDetailsInDTO.getCountry());
        String country = "country";
        revenueDetailsInDTO.setCountry(country);
        assertEquals(country, revenueDetailsInDTO.getCountry());
        
        assertNull(revenueDetailsInDTO.getStartDate());
        LocalDate startDate = LocalDate.of(2022, 9, 15);
        revenueDetailsInDTO.setStartDate(startDate);
        assertEquals(startDate, revenueDetailsInDTO.getStartDate());
        
        assertNull(revenueDetailsInDTO.getEndDate());
        LocalDate endDate = LocalDate.of(2022, 9, 30);
        revenueDetailsInDTO.setEndDate(endDate);
        assertEquals(endDate, revenueDetailsInDTO.getEndDate());
    }
    
    @Test
    public void testToString() {
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        String businessName = "Business Name";
        String country = "country";
        LocalDate startDate = LocalDate.of(2022, 9, 15);
        LocalDate endDate = LocalDate.of(2022, 9, 30);
        
        revenueDetailsInDTO.setBusinessName(businessName);
        revenueDetailsInDTO.setCountry(country);
        revenueDetailsInDTO.setStartDate(startDate);
        revenueDetailsInDTO.setEndDate(endDate);
        
        assertEquals("RevenueDetailsInDTO [country=country, businessName=Business Name, startDate=2022-09-15, endDate=2022-09-30]", 
                revenueDetailsInDTO.toString());
    }
    
    @Test
    public void testEqualsAndHashcode() {
        String businessName = "Business Name";
        String country = "country";
        LocalDate startDate = LocalDate.of(2022, 9, 15);
        LocalDate endDate = LocalDate.of(2022, 9, 30);
        
        RevenueDetailsInDTO revenueDetailsInDTO1 = buildRevenueDetailsInDTO(country, businessName, startDate, endDate);
        
        assertEquals(revenueDetailsInDTO1, revenueDetailsInDTO1);
        assertEquals(revenueDetailsInDTO1.hashCode(), revenueDetailsInDTO1.hashCode());
        
        assertNotEquals(revenueDetailsInDTO1, new Object());
        
        RevenueDetailsInDTO revenueDetailsInDTO2 = buildRevenueDetailsInDTO(country, businessName, startDate, endDate);
        
        assertEquals(revenueDetailsInDTO1, revenueDetailsInDTO2);
        assertEquals(revenueDetailsInDTO1.hashCode(), revenueDetailsInDTO2.hashCode());

        revenueDetailsInDTO2 = buildRevenueDetailsInDTO(country+"1", businessName, startDate, endDate);
        assertNotEquals(revenueDetailsInDTO1, revenueDetailsInDTO2);
        assertNotEquals(revenueDetailsInDTO1.hashCode(), revenueDetailsInDTO2.hashCode());
        
        revenueDetailsInDTO2 = buildRevenueDetailsInDTO(country, businessName+"1", startDate, endDate);
        assertNotEquals(revenueDetailsInDTO1, revenueDetailsInDTO2);
        assertNotEquals(revenueDetailsInDTO1.hashCode(), revenueDetailsInDTO2.hashCode());
        
        revenueDetailsInDTO2 = buildRevenueDetailsInDTO(country, businessName, LocalDate.of(2022, 9, 17), endDate);
        assertNotEquals(revenueDetailsInDTO1, revenueDetailsInDTO2);
        assertNotEquals(revenueDetailsInDTO1.hashCode(), revenueDetailsInDTO2.hashCode());
        
        revenueDetailsInDTO2 = buildRevenueDetailsInDTO(country, businessName, startDate, LocalDate.of(2022, 2, 15));
        assertNotEquals(revenueDetailsInDTO1, revenueDetailsInDTO2);
        assertNotEquals(revenueDetailsInDTO1.hashCode(), revenueDetailsInDTO2.hashCode());
        
    }
    
    private RevenueDetailsInDTO buildRevenueDetailsInDTO(String country, String businessName, LocalDate startDate, LocalDate endDate) {
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        revenueDetailsInDTO.setBusinessName(businessName);
        revenueDetailsInDTO.setCountry(country);
        revenueDetailsInDTO.setStartDate(startDate);
        revenueDetailsInDTO.setEndDate(endDate);
        
        return revenueDetailsInDTO;
    }

}
