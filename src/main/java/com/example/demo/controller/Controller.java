package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Patient;
import com.example.demo.service.PateintService;


@RestController
@RequestMapping("/patient")
public class Controller {
	
	@Autowired
	private PateintService service;
	
	@PostMapping("/Createpatient")
	public ResponseEntity<?> savePatient(@RequestBody Patient patient)
	{
		Patient dtr= this.service.savePatient(patient);
		return new ResponseEntity<Patient>(dtr,HttpStatus.CREATED);
	}
	@GetMapping("/Getpatient")
	public ResponseEntity<?> getPatient()
	{
		List<Patient> listDtr= this.service.getPatient();
		return new ResponseEntity<List<Patient>>(listDtr,HttpStatus.OK);
	}

	
	@GetMapping("/GetpatientById/{p_id}")
	public ResponseEntity<?> GetPatientById(@PathVariable long p_id)
	{
		try {
		Patient dtr = this.service.GetPatientById(p_id);
		return new ResponseEntity<Patient>(dtr,HttpStatus.OK);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
