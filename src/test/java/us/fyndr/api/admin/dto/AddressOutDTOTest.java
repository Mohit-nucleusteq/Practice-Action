package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AddressOutDTOTest {

	@Test
	public void testGetterAndSetter() {
		AddressOutDTO addressOutDTO = new AddressOutDTO();
		
		assertNull(addressOutDTO.getAddressLine1());
		String addressLine1 = "addressLine1";
		addressOutDTO.setAddressLine1(addressLine1);
		assertEquals(addressLine1, addressOutDTO.getAddressLine1());
		
		assertNull(addressOutDTO.getAddressLine2());
		String addressLine2 = "addressLine2";
		addressOutDTO.setAddressLine2(addressLine2);
		assertEquals(addressLine2, addressOutDTO.getAddressLine2());
		
		assertNull(addressOutDTO.getCity());
		String city = "city";
		addressOutDTO.setCity(city);
		assertEquals(city, addressOutDTO.getCity());
		
		assertNull(addressOutDTO.getCountry());
		String country = "country";
		addressOutDTO.setCountry(country);
		assertEquals(country, addressOutDTO.getCountry());
		
		assertNull(addressOutDTO.getPostalCode());
		String postalCode = "postalCode";
		addressOutDTO.setPostalCode(postalCode);
		assertEquals(postalCode, addressOutDTO.getPostalCode());
		
		assertNull(addressOutDTO.getState());
		String state = "state";
		addressOutDTO.setState(state);
		assertEquals(state, addressOutDTO.getState());
	}
	
	@Test
	public void testToString() {
		AddressOutDTO addressOutDTO = new AddressOutDTO();
		
		String addressLine1 = "addressLine1";
		addressOutDTO.setAddressLine1(addressLine1);
		
		String addressLine2 = "addressLine2";
		addressOutDTO.setAddressLine2(addressLine2);
		
		String city = "city";
		addressOutDTO.setCity(city);
		
		String country = "country";
		addressOutDTO.setCountry(country);
		
		String postalCode = "postalCode";
		addressOutDTO.setPostalCode(postalCode);
		
		String state = "state";
		addressOutDTO.setState(state);
		
		assertEquals("AddressOutDTO [addressLine1=addressLine1, addressLine2=addressLine2, state=state, "
				+ "country=country, postalCode=postalCode, city=city]", addressOutDTO.toString());
	}
	
	@Test
	public void testEqualsAndHashcode() {
		String addressLine1 = "addressLine1";
		String addressLine2 = "addressLine2";
		String city = "city";
		String country = "country";
		String postalCode = "postalCode";
		String state = "state";
		
		AddressOutDTO addressOutDTO1 = buildAddressOutDTO(addressLine1, addressLine2, city, country, postalCode, state);
		
		assertEquals(addressOutDTO1, addressOutDTO1);
		assertEquals(addressOutDTO1.hashCode(), addressOutDTO1.hashCode());
		
		assertNotEquals(addressOutDTO1, new Object());
		
		AddressOutDTO addressOutDTO2 = buildAddressOutDTO(addressLine1, addressLine2, city, country, postalCode, state);
		assertEquals(addressOutDTO1, addressOutDTO2);
		assertEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
		
		addressOutDTO2 = buildAddressOutDTO(addressLine1 + " ", addressLine2, city, country, postalCode, state);
		assertNotEquals(addressOutDTO1, addressOutDTO2);
		assertNotEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
		
		addressOutDTO2 = buildAddressOutDTO(addressLine1, addressLine2 + " ", city, country, postalCode, state);
		assertNotEquals(addressOutDTO1, addressOutDTO2);
		assertNotEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
		
		addressOutDTO2 = buildAddressOutDTO(addressLine1, addressLine2, city + " ", country, postalCode, state);
		assertNotEquals(addressOutDTO1, addressOutDTO2);
		assertNotEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
		
		addressOutDTO2 = buildAddressOutDTO(addressLine1, addressLine2, city, country + " ", postalCode, state);
		assertNotEquals(addressOutDTO1, addressOutDTO2);
		assertNotEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
		
		addressOutDTO2 = buildAddressOutDTO(addressLine1, addressLine2, city, country, postalCode + " ", state);
		assertNotEquals(addressOutDTO1, addressOutDTO2);
		assertNotEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
		
		addressOutDTO2 = buildAddressOutDTO(addressLine1, addressLine2, city, country, postalCode, state + " ");
		assertNotEquals(addressOutDTO1, addressOutDTO2);
		assertNotEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
		
		addressOutDTO1 = new AddressOutDTO();
		addressOutDTO2 = new AddressOutDTO();
		assertEquals(addressOutDTO1, addressOutDTO2);
		assertEquals(addressOutDTO1.hashCode(), addressOutDTO2.hashCode());
	}
	
	private AddressOutDTO buildAddressOutDTO(String addressLine1, String addressLine2, String city, String country, String postalCode, String state) {
		AddressOutDTO addressOutDTO = new AddressOutDTO();
		
		addressOutDTO.setAddressLine1(addressLine1);
		addressOutDTO.setAddressLine2(addressLine2);
		addressOutDTO.setCity(city);
		addressOutDTO.setCountry(country);
		addressOutDTO.setPostalCode(postalCode);
		addressOutDTO.setState(state);
		
		return addressOutDTO;
	}
}
