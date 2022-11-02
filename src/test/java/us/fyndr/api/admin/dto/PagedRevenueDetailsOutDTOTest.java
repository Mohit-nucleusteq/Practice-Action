package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PagedRevenueDetailsOutDTOTest {
    
    @Test
    public void testGetterAndSetter() {
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();

        assertFalse(pagedRevenueDetailsOutDTO.isLast());
        boolean isLast = true;
        pagedRevenueDetailsOutDTO.setLast(isLast);
        assertEquals(isLast, pagedRevenueDetailsOutDTO.isLast());

        assertNull(pagedRevenueDetailsOutDTO.getRevenueDetails());
        List<RevenueDetailsOutDTO> revenueDetailsOutDTO = new ArrayList<>();
        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTO);
        assertEquals(revenueDetailsOutDTO, pagedRevenueDetailsOutDTO.getRevenueDetails());

        assertNull(pagedRevenueDetailsOutDTO.getCount());
        Long count = 5L;
        pagedRevenueDetailsOutDTO.setCount(count);
        assertEquals(count, pagedRevenueDetailsOutDTO.getCount());
    }

    @Test
    public void testToString() {
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
                
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        String businessName = "businessName";
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(506.235);
        revenueDetailsOutDTO.setCurrency("currency");
        revenueDetailsOutDTO.setCurrencySymbol("currencySymbol");
        revenueDetailsOutDTO.setOffers(25.621);
        revenueDetailsOutDTO.setInteraction(55.120);
        revenueDetailsOutDTO.setObjId(1L);

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        businessName = "businessName1";
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(506.235);
        revenueDetailsOutDTO.setCurrency("currency");
        revenueDetailsOutDTO.setCurrencySymbol("currencySymbol");
        revenueDetailsOutDTO.setCatalog(21.621);
        revenueDetailsOutDTO.setPromo(53.120);
        revenueDetailsOutDTO.setObjId(2L);

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(5L);

        assertEquals(
                "PagedRevenueDetailsOutDTO [last=true, revenueDetails=[RevenueDetailsOutDTO [businessName=businessName, objId=1, "
                + "offers=25.62, promo=0.0, catalog=0.0, interaction=55.12, totalRevenue=506.24, currency=currency, currencySymbol=currencySymbol], "
                + "RevenueDetailsOutDTO [businessName=businessName1, objId=2, offers=0.0, promo=53.12, catalog=21.62, "
                + "interaction=0.0, totalRevenue=506.24, currency=currency, currencySymbol=currencySymbol]], count=5]",
                 pagedRevenueDetailsOutDTO.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        boolean last = true;
        
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        String businessName = "businessName";
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(506.235);
        revenueDetailsOutDTO.setOffers(25.621);
        revenueDetailsOutDTO.setInteraction(55.120);
        revenueDetailsOutDTO.setCurrency("currency");
        revenueDetailsOutDTO.setCurrencySymbol("currencySymbol");
        revenueDetailsOutDTO.setObjId(1L);

        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        businessName = "businessName1";
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(506.235);
        revenueDetailsOutDTO.setCurrency("currency");
        revenueDetailsOutDTO.setCurrencySymbol("currencySymbol");
        revenueDetailsOutDTO.setCatalog(21.621);
        revenueDetailsOutDTO.setPromo(53.120);
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        revenueDetailsOutDTO.setObjId(2L);

        Long count = 6L;

        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO1 = buildPagedRevenueDetails(last, revenueDetailsOutDTOList, count);
        assertEquals(pagedRevenueDetailsOutDTO1, pagedRevenueDetailsOutDTO1);
        assertEquals(pagedRevenueDetailsOutDTO1.hashCode(), pagedRevenueDetailsOutDTO1.hashCode());

        assertNotEquals(pagedRevenueDetailsOutDTO1, new Object());

        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO2 = buildPagedRevenueDetails(last, revenueDetailsOutDTOList, count);
        assertEquals(pagedRevenueDetailsOutDTO1, pagedRevenueDetailsOutDTO2);
        assertEquals(pagedRevenueDetailsOutDTO1.hashCode(), pagedRevenueDetailsOutDTO2.hashCode());

        pagedRevenueDetailsOutDTO2 = buildPagedRevenueDetails(false, revenueDetailsOutDTOList, count);;
        assertNotEquals(pagedRevenueDetailsOutDTO1, pagedRevenueDetailsOutDTO2);
        assertNotEquals(pagedRevenueDetailsOutDTO1.hashCode(), pagedRevenueDetailsOutDTO2.hashCode());

        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList1 = new ArrayList<>();
        revenueDetailsOutDTOList1.add(revenueDetailsOutDTO);

        pagedRevenueDetailsOutDTO2 = buildPagedRevenueDetails(last, revenueDetailsOutDTOList1, count);
        assertNotEquals(pagedRevenueDetailsOutDTO1, pagedRevenueDetailsOutDTO2);
        assertNotEquals(pagedRevenueDetailsOutDTO1.hashCode(), pagedRevenueDetailsOutDTO2.hashCode());
        
        pagedRevenueDetailsOutDTO2 = buildPagedRevenueDetails(last, revenueDetailsOutDTOList1, 78L);
        assertNotEquals(pagedRevenueDetailsOutDTO1, pagedRevenueDetailsOutDTO2);
        assertNotEquals(pagedRevenueDetailsOutDTO1.hashCode(), pagedRevenueDetailsOutDTO2.hashCode());

    }

    private PagedRevenueDetailsOutDTO buildPagedRevenueDetails(boolean last, List<RevenueDetailsOutDTO> revenueDetailsOutDTOList, Long count) {
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();

        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(last);
        pagedRevenueDetailsOutDTO.setCount(count);

        return pagedRevenueDetailsOutDTO;
    }
}
