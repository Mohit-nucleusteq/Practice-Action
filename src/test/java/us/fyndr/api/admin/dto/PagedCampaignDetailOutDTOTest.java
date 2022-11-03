package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PagedCampaignDetailOutDTOTest {
    @Test
    public void testGetterAndSetter() {
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();

        assertFalse(pagedCampaignDetailOutDTO.isLast());
 
        assertNull(pagedCampaignDetailOutDTO.getCampaignDetails());
        List<CampaignDetailsOutDTO> campaignList = new ArrayList<>();
        pagedCampaignDetailOutDTO.setCampaignDetails(campaignList);
        assertEquals(campaignList, pagedCampaignDetailOutDTO.getCampaignDetails());

        Long count = 67L;
        pagedCampaignDetailOutDTO.setCount(count);
        assertEquals(count, pagedCampaignDetailOutDTO.getCount());

        Boolean isLast = true;
        pagedCampaignDetailOutDTO.setLast(isLast);
        assertEquals(isLast, pagedCampaignDetailOutDTO.isLast());
    }

    @Test
    public void testToString() {
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();
        Long objid = 1l;
        campaignDetailsOutDTO.setObjId(objid);
        Integer activeOffer = 1;
        campaignDetailsOutDTO.setActiveOffers(activeOffer);
        String businessName = "businessName";
        campaignDetailsOutDTO.setBusinessName(businessName);
        String campaignName = "campaignName";
        campaignDetailsOutDTO.setCampaignName(campaignName);
        String industryType = "industryType";
        campaignDetailsOutDTO.setIndustryType(industryType);
        String campaignType = "campaignType";
        campaignDetailsOutDTO.setCampaignType(campaignType);
        LocalDate endDate = LocalDate.now();
        campaignDetailsOutDTO.setEndDate(endDate);
        Integer offerSold = 12;
        campaignDetailsOutDTO.setOfferSold(offerSold);
        String currency = "USD";
        campaignDetailsOutDTO.setCurrency(currency);
        String currencySymbol = "$";
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        Double totalOfferSoldAmount = 123.43;
        campaignDetailsOutDTO.setTotalOfferSoldAmount(totalOfferSoldAmount);
        Integer totalOffer = 13;
        campaignDetailsOutDTO.setTotalOffers(totalOffer);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);
        pagedCampaignDetailOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        boolean last = true;
        pagedCampaignDetailOutDTO.setLast(last);

        Long count = 67L;
        pagedCampaignDetailOutDTO.setCount(count);

        assertEquals(
                "PagedCampaignDetailOutDTO [last=true, campaignDetails=[CampaignDetailsOutDTO [objId=1, businessName=businessName, campaignName=campaignName, activeOffers=1, totalOffers=13, industryType=industryType, campaignType=campaignType, offerSold=12, totalOfferSoldAmount=123.43, endDate="
                        + campaignDetailsOutDTO.getEndDate() + ", currency=USD, currencySymbol=$]], count=67]",
                pagedCampaignDetailOutDTO.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        boolean last = true;

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();
        Integer activeOffer = 1;
        campaignDetailsOutDTO.setActiveOffers(activeOffer);
        String businessName = "businessName";
        campaignDetailsOutDTO.setBusinessName(businessName);
        String campaignName = "campaignName";
        campaignDetailsOutDTO.setCampaignName(campaignName);
        String industryType = "industryType";
        campaignDetailsOutDTO.setIndustryType(industryType);
        String campaignType = "campaignType";
        campaignDetailsOutDTO.setCampaignType(campaignType);
        LocalDate endDate = LocalDate.now();
        campaignDetailsOutDTO.setEndDate(endDate);
        Integer offerSold = 12;
        campaignDetailsOutDTO.setOfferSold(offerSold);
        Double totalOfferSoldAmount = 123.43;
        campaignDetailsOutDTO.setTotalOfferSoldAmount(totalOfferSoldAmount);
        Integer totalOffer = 13;
        campaignDetailsOutDTO.setTotalOffers(totalOffer);
        String currency = "USD";
        campaignDetailsOutDTO.setCurrency(currency);
        String currencySymbol = "$";
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();

        Long count = 67L;

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO1 = setData(last, campaignDetailsOutDTOList, count);
        assertEquals(pagedCampaignDetailOutDTO1, pagedCampaignDetailOutDTO1);
        assertEquals(pagedCampaignDetailOutDTO1.hashCode(), pagedCampaignDetailOutDTO1.hashCode());

        assertNotEquals(pagedCampaignDetailOutDTO1, new Object());

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = setData(last, campaignDetailsOutDTOList, count);
        assertEquals(pagedCampaignDetailOutDTO1, pagedCampaignDetailOutDTO2);
        assertEquals(pagedCampaignDetailOutDTO1.hashCode(), pagedCampaignDetailOutDTO2.hashCode());

        pagedCampaignDetailOutDTO2 = setData(false, campaignDetailsOutDTOList, count);
        assertNotEquals(pagedCampaignDetailOutDTO1, pagedCampaignDetailOutDTO2);
        assertNotEquals(pagedCampaignDetailOutDTO1.hashCode(), pagedCampaignDetailOutDTO2.hashCode());

        List<CampaignDetailsOutDTO> CampaignDetails1 = new ArrayList<CampaignDetailsOutDTO>();
        CampaignDetails1.add(campaignDetailsOutDTO);

        pagedCampaignDetailOutDTO2 = setData(last, CampaignDetails1, count);

        assertNotEquals(pagedCampaignDetailOutDTO1, pagedCampaignDetailOutDTO2);
        assertNotEquals(pagedCampaignDetailOutDTO1.hashCode(), pagedCampaignDetailOutDTO2.hashCode());

        pagedCampaignDetailOutDTO1 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();

        assertEquals(pagedCampaignDetailOutDTO1, pagedCampaignDetailOutDTO2);
        assertEquals(pagedCampaignDetailOutDTO1.hashCode(), pagedCampaignDetailOutDTO2.hashCode());
    }

    private PagedCampaignDetailOutDTO setData(boolean last, List<CampaignDetailsOutDTO> campaignList, Long count) {
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();

        pagedCampaignDetailOutDTO.setLast(last);
        pagedCampaignDetailOutDTO.setCampaignDetails(campaignList);
        pagedCampaignDetailOutDTO.setCount(count);

        return pagedCampaignDetailOutDTO;
    }
}
