package com.foodorderingsys.services.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.foodorderingsys.dao.AdminRepository;
import com.foodorderingsys.model.Admin;
import com.foodorderingsys.services.AdminServices;

@RunWith(SpringRunner.class)
@SpringBootTest
class AdminServicesTest {
     @MockBean
     AdminRepository adminRepository;
     @Autowired
     AdminServices adminServices;
     
	@Test
	void  getAdminTest() {
		when(adminRepository.findAll()).thenReturn(Stream
				.of(new Admin(11,"sharma@gmail.com","arun sharma","arun12@B",760920111)).collect(Collectors.toList()));
		assertEquals(1,adminServices.getAdmin().size());
		System.out.println(adminServices.getAdmin().size());
	}
	@Test
	void addAdminTest() {
		Admin admin=new Admin(12,"mrtn123@gmail.com","mohit","MoA!208@C",810976168);
		when(adminRepository.save(admin)).thenReturn(admin);
		System.out.println(admin);
		assertEquals(admin,adminServices.addAdmin(admin));
	}
}


	

