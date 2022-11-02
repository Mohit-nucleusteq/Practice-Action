package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;

public class IndividualOutDTOTest {

	@Test
	public void testGetterAndSetter() {
		IndividualOutDTO individualOutDTO = new IndividualOutDTO();

		assertNull(individualOutDTO.getObjId());
		Long objId = 5L;
		individualOutDTO.setObjId(objId);
		assertEquals(objId, individualOutDTO.getObjId());

		assertNull(individualOutDTO.getAddress());
		AddressOutDTO addressOutDTO = new AddressOutDTO();
		individualOutDTO.setAddress(addressOutDTO);
		assertEquals(addressOutDTO, individualOutDTO.getAddress());

		assertNull(individualOutDTO.getBusinessName());
		String businessName = "businessName";
		individualOutDTO.setBusinessName(businessName);
		assertEquals(businessName, individualOutDTO.getBusinessName());

		assertNull(individualOutDTO.getCreateDt());
		Instant createDt = Instant.now();
		individualOutDTO.setCreateDt(createDt);
		assertEquals(createDt, individualOutDTO.getCreateDt());

		assertNull(individualOutDTO.getEmail());
		String email = "email";
		individualOutDTO.setEmail(email);
		assertEquals(email, individualOutDTO.getEmail());

		assertNull(individualOutDTO.isBusiness());
		boolean isBusiness = true;
		individualOutDTO.setIsBusiness(isBusiness);
		assertEquals(isBusiness, individualOutDTO.isBusiness());

		assertNull(individualOutDTO.getName());
		String name = "name";
		individualOutDTO.setName(name);
		assertEquals(name, individualOutDTO.getName());

		assertNull(individualOutDTO.getPhone());
		PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
		individualOutDTO.setPhone(phoneNumberOutDTO);
		assertEquals(phoneNumberOutDTO, individualOutDTO.getPhone());

		assertNull(individualOutDTO.getStatus());
		AccountStatus accountStatus = AccountStatus.ACTIVE;
		individualOutDTO.setStatus(accountStatus);
		assertEquals(accountStatus, individualOutDTO.getStatus());

		assertNull(individualOutDTO.getWebsite());
		String website = "website";
		individualOutDTO.setWebsite(website);
		assertEquals(website, individualOutDTO.getWebsite());
	}

	@Test
	public void testToString() {
		IndividualOutDTO individualOutDTO = new IndividualOutDTO();

		Long objId = 5L;
		individualOutDTO.setObjId(objId);

		AddressOutDTO addressOutDTO = new AddressOutDTO();
		individualOutDTO.setAddress(addressOutDTO);

		String businessName = "businessName";
		individualOutDTO.setBusinessName(businessName);

		Instant createDt = Instant.now();
		individualOutDTO.setCreateDt(createDt);

		String email = "email";
		individualOutDTO.setEmail(email);

		boolean isBusiness = true;
		individualOutDTO.setIsBusiness(isBusiness);

		String name = "name";
		individualOutDTO.setName(name);

		PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
		individualOutDTO.setPhone(phoneNumberOutDTO);

		AccountStatus accountStatus = AccountStatus.ACTIVE;
		individualOutDTO.setStatus(accountStatus);

		String website = "website";
		individualOutDTO.setWebsite(website);

		assertEquals(
				"IndividualOutDTO [objId=5, address=AddressOutDTO [addressLine1=null, addressLine2=null, state=null"
						+ ", country=null, postalCode=null, city=null], createDt=" + createDt
						+ ", name=name, status=ACTIVE, isBusiness=true, "
						+ "businessName=businessName, website=website, phone=PhoneNumberOutDTO [countryCode=null, phoneNumber=null], "
						+ "email=email]",
				individualOutDTO.toString());
	}

	@Test
	public void testEqualsAndHashcode() {
		AddressOutDTO addressOutDTO = new AddressOutDTO();
		Long objId = 5L;
		String businessName = "businessName";
		Instant createDt = Instant.now();
		String email = "email";
		boolean isBusiness = true;
		String name = "name";
		PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
		AccountStatus accountStatus = AccountStatus.ACTIVE;
		String website = "website";

		IndividualOutDTO individualOutDTO1 = build(addressOutDTO, businessName, createDt, email, isBusiness, name,
				phoneNumberOutDTO, accountStatus, website, objId);

		assertEquals(individualOutDTO1, individualOutDTO1);
		assertEquals(individualOutDTO1.hashCode(), individualOutDTO1.hashCode());

		assertNotEquals(individualOutDTO1, new Object());

		IndividualOutDTO individualOutDTO2 = build(addressOutDTO, businessName, createDt, email, isBusiness, name,
				phoneNumberOutDTO, accountStatus, website, objId);
		assertEquals(individualOutDTO1, individualOutDTO2);
		assertEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(null, businessName, createDt, email, isBusiness, name, phoneNumberOutDTO,
				accountStatus, website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName + " ", createDt, email, isBusiness, name,
				phoneNumberOutDTO, accountStatus, website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, Instant.MIN, email, isBusiness, name, phoneNumberOutDTO,
				accountStatus, website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, createDt, email + " ", isBusiness, name,
				phoneNumberOutDTO, accountStatus, website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, createDt, email, false, name, phoneNumberOutDTO,
				accountStatus, website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, createDt, email, isBusiness, name + " ",
				phoneNumberOutDTO, accountStatus, website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, createDt, email, isBusiness, name, null, accountStatus,
				website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, createDt, email, isBusiness, name, phoneNumberOutDTO,
				AccountStatus.INACTIVE, website, objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, createDt, email, isBusiness, name, phoneNumberOutDTO,
				accountStatus, website + " ", objId);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO2 = build(addressOutDTO, businessName, createDt, email, isBusiness, name, phoneNumberOutDTO,
				accountStatus, website, 6L);
		assertNotEquals(individualOutDTO1, individualOutDTO2);
		assertNotEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());

		individualOutDTO1 = new IndividualOutDTO();
		individualOutDTO2 = new IndividualOutDTO();
		assertEquals(individualOutDTO1, individualOutDTO2);
		assertEquals(individualOutDTO1.hashCode(), individualOutDTO2.hashCode());
	}

	private IndividualOutDTO build(AddressOutDTO addressOutDTO, String businessName, Instant createDt, String email,
			boolean isBusiness, String name, PhoneNumberOutDTO phoneNumberOutDTO, AccountStatus accountStatus,
			String website, Long objId) {
		IndividualOutDTO individualOutDTO = new IndividualOutDTO();

		individualOutDTO.setAddress(addressOutDTO);

		individualOutDTO.setBusinessName(businessName);

		individualOutDTO.setCreateDt(createDt);

		individualOutDTO.setEmail(email);

		individualOutDTO.setIsBusiness(isBusiness);

		individualOutDTO.setName(name);

		individualOutDTO.setPhone(phoneNumberOutDTO);

		individualOutDTO.setStatus(accountStatus);

		individualOutDTO.setWebsite(website);

		individualOutDTO.setObjId(objId);

		return individualOutDTO;
	}
}
