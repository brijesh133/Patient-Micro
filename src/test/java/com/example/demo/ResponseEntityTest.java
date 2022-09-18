package com.example.demo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.Entity.Patient;
import com.example.demo.controller.Controller;
import com.example.demo.service.PateintService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes={ResponseEntityTest.class})
public class ResponseEntityTest {
	
	@Mock
	PateintService patService;

	@InjectMocks
	Controller con;

	List<Patient> patList;

	Patient pat;

	ResponseEntity<?> resp;

	@Test
	@Order(1)
	public void testGetAllPatients() {

		patList = new ArrayList<>();
		patList.add(new Patient(1,"ajay","Gagan", new Date(),"Lt"));
		patList.add(new Patient(2,"bhagya","Kumar", new Date(),"Lt"));

		when( patService.getPatient()).thenReturn(patList);

		resp = con.getPatient();

		assertEquals(HttpStatus.OK, resp.getStatusCode());
	}

	@Test
	@Order(2)
	public void test_addPatient(){

		pat = new Patient(4,"Jagadesh-Virat","Rithesh",new Date(),"Lt");

		when( patService.savePatient(pat)).thenReturn(pat);

		resp = con.savePatient(pat);

		assertEquals(HttpStatus.CREATED, resp.getStatusCode());
	}

	@Test
	@Order(3)
	public void test_getallPatientbyId() {

		pat = new Patient(1,"ajay","Gagan",null,"Lt");

		int id = 1;

		when( patService.GetPatientById(id)).thenReturn(pat);

		ResponseEntity<?> resp = con.GetPatientById(id);
		
		String body=resp.getBody().toString();
		String id1=String.valueOf(body.charAt(11));
		
		 assertEquals(id+"",id1);

		assertEquals(HttpStatus.OK, resp.getStatusCode());

	}

}
