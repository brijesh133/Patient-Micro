package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Entity.Patient;
import com.example.demo.Exception.PatientNotFoundException;
import com.example.demo.Repo.PatientRepo;

@Service
public class PatientImple implements PateintService {

	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	RestTemplate template;
	
	@Override
	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		Patient p= patientRepo.save(patient);
		if(template!=null)
		{
		template.getForObject("http://localhost:9090/doctor/update/count/"+patient.getVisitedDoc()+"/1", String.class);
		}
		return p;
	}

	@Override
	public List<Patient> getPatient() {
		// TODO Auto-generated method stub
		return patientRepo.findAll();
	}

	@Override
	public Patient GetPatientById(long p_id) {
		// TODO Auto-generated method stub
		
		 List<Patient> patienList=patientRepo.findAll();
			
	      Patient pic=null;
			
			for(Patient piclist:patienList)
			{
				if(piclist.getId()==p_id)
				{
					pic=piclist;
					return pic;
				}
				
			}
			
			throw new PatientNotFoundException("No Patient found with id : "+p_id);
	}

}
