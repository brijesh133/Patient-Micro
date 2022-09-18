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

import com.example.demo.Entity.Patient;
import com.example.demo.Repo.PatientRepo;
import com.example.demo.service.PatientImple;

import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = {PatientsApplicationTests.class})
class PatientsApplicationTests {

	@Mock
	PatientRepo IPatientRepo;
	
	@InjectMocks
	PatientImple PatientServiceImpl;
	
	List<Patient> patList;
	
	Patient pat;
	
	
	@Test
	@Order(1)
	public void testGetAllPatients() {
		
		patList = new ArrayList<>();
		patList.add(new Patient(1,"ajay","Gagan", new Date(),"Lt"));
		patList.add(new Patient(2,"bhagya","Kumar", new Date(),"Lt"));
		
       when(IPatientRepo.findAll()).thenReturn(patList);
		
		assertEquals(2,PatientServiceImpl.getPatient().size() );	
	}
	
	@Test
	@Order(2)
	public void test_addPatient(){
		
		pat = new Patient(4,"Brijesh","Rithesh",new Date(),"Lt");
		
		
		when( IPatientRepo.save(pat)).thenReturn(pat);
		
		assertEquals(pat,PatientServiceImpl.savePatient(pat));	
		
		
	}
	
	@Test
	@Order(3)
	public void test_getallPatientbyId() {
		
		patList = new ArrayList<>();
		patList.add(new Patient(1,"ajay","Gagan", new Date(),"Lt"));
		patList.add(new Patient(2,"bhagya","Kumar", new Date(),"Lt"));
		
		int id = 2;
		
		 when(IPatientRepo.findAll()).thenReturn(patList);
		
		assertEquals(2,PatientServiceImpl.GetPatientById(id).getId());
		 
	}

}
