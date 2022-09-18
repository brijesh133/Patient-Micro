package com.example.demo.service;

import java.util.List;

import com.example.demo.Entity.Patient;

public interface PateintService {

	Patient savePatient(Patient patient);

	List<Patient> getPatient();

	Patient GetPatientById(long p_id);

}
