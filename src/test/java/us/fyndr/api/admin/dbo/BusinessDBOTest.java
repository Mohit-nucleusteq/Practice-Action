package us.fyndr.api.admin.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class BusinessDBOTest {
    @Test
    public void testGetterAndSetter() {
        Business businessDBO = new Business();

        assertNull(businessDBO.getBusinessName());
        String businessName = "business name";
        businessDBO.setBusinessName(businessName);
        assertEquals(businessName, businessDBO.getBusinessName());

        assertNull(businessDBO.getBizType());
        String businessType = "business type";
        businessDBO.setBizType(businessType);
        assertEquals(businessType, businessDBO.getBizType());

        assertNull(businessDBO.getObjId());
        long objid = 12l;
        businessDBO.setObjId(objid);
        assertEquals(objid, businessDBO.getObjId());

        assertNull(businessDBO.getWebsite());
        String website = "website";
        businessDBO.setWebsite(website);
        assertEquals(website, businessDBO.getWebsite());

    }

    @Test
    public void testToString() {
        Business businessDBO = new Business();

        String businessName = "business name";
        businessDBO.setBusinessName(businessName);

        long objid = 12;
        businessDBO.setObjId(objid);

        String website = "website";
        businessDBO.setWebsite(website);

        String businessType = "business type";
        businessDBO.setBizType(businessType);

        assertEquals("Business [objId=12, businessName=business name, bizType=business type, website=website]",
                businessDBO.toString());

    }

    @Test
    public void testEqualsAndHashcode() {
        String businessName = "business name";
        long objid = 12;
        String website = "website";
        String businessType = "business type";

        Business businessDBO1 = buildBusinessDbo(businessName, objid, website, businessType);

        assertEquals(businessDBO1, businessDBO1);
        assertEquals(businessDBO1.hashCode(), businessDBO1.hashCode());
        assertNotEquals(businessDBO1, new Object());

        Business businessDBO2 = buildBusinessDbo(businessName, objid, website, businessType);
        assertEquals(businessDBO1, businessDBO2);
        assertEquals(businessDBO1.hashCode(), businessDBO2.hashCode());

        businessDBO2 = buildBusinessDbo(businessName + " ", objid, website, businessType);
        assertNotEquals(businessDBO1, businessDBO2);
        assertNotEquals(businessDBO1.hashCode(), businessDBO2.hashCode());

        businessDBO2 = buildBusinessDbo(businessName, 1234567, website, businessType);
        assertNotEquals(businessDBO1, businessDBO2);
        assertNotEquals(businessDBO1.hashCode(), businessDBO2.hashCode());

        businessDBO2 = buildBusinessDbo(businessName, objid, website + " ", businessType);
        assertNotEquals(businessDBO1, businessDBO2);
        assertNotEquals(businessDBO1.hashCode(), businessDBO2.hashCode());

        businessDBO2 = buildBusinessDbo(businessName, objid, website, businessType + " ");
        assertNotEquals(businessDBO1, businessDBO2);
        assertNotEquals(businessDBO1.hashCode(), businessDBO2.hashCode());

        businessDBO1 = new Business();
        businessDBO2 = new Business();
        assertEquals(businessDBO1, businessDBO2);
        assertEquals(businessDBO1.hashCode(), businessDBO2.hashCode());
    }

    private Business buildBusinessDbo(String businessName, long objid, String website, String businessType) {
        Business businessDBO = new Business();

        businessDBO.setBusinessName(businessName);

        businessDBO.setObjId(objid);

        businessDBO.setWebsite(website);

        businessDBO.setBizType(businessType);

        return businessDBO;
    }

}