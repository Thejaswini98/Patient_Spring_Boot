package com.cg.patient.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.patient.exception.PatientException;
import com.cg.patient.repository.PatientRepository;
import com.cg.patient.domain.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
@Service
public class PatientService {
	
	@Autowired
	PatientRepository repository;
	
	Doctor doctor1 = new Doctor("EDD","Orthopaedics",38383);
	List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor1});
	
	Hospital hospital1 = new Hospital("ABC","XYZ",83828,new String[]{"Ortho","Heart"},new boolean[]{true,true,false},doctorList);
	
	public Patient saveOrUpdate(Patient patient) {

		try {
			patient.setPatientIdentifier(patient.getPatientIdentifier().toUpperCase());
			return repository.save(patient);
		} catch (Exception e) {
			throw new PatientException("PatientIdentifier " + patient.getPatientIdentifier() + " already available");
		}

	}

	public Patient findPatientByPatientIdentifier(String patientIdentifier) {
		Patient patient = repository.findByPatientIdentifier(patientIdentifier.toUpperCase());
		if (patient == null) {
			throw new PatientException("PatientIdentifier " + patientIdentifier + " not available");
			
		}
		return patient;

	}
	
	public Iterable<Patient> findAllPatients(){
		return repository.findAll();
		
	}
	
	public void deletePatientByPatientIdentifier(String patientIdentifier) {
		Patient patient=findPatientByPatientIdentifier(patientIdentifier.toUpperCase());
		if(patient==null) {
			throw new PatientException("PatientIdentifier " + patientIdentifier + " not available");
		}
		repository.delete(patient);
	}
	
	public boolean searchHospitalByBedAvailability(String hospitalName) {
		boolean availability = false;
		if(this.hospital1.getHospitalName().equalsIgnoreCase(hospitalName)) {
			boolean[] bedsAvailable = hospital1.getBeds();
			for(Boolean b:bedsAvailable) {
				if(b) {
					availability = true;
					break;
				}
			}
		}
		return availability;
	}
	public boolean searchHospitalByDoctorAvailability(String hospitalName, String doctorName) {
		if(this.hospital1.getHospitalName().equalsIgnoreCase(hospitalName)) {
			List<Doctor> DoctorList = hospital1.getDoctorList();
			
			for(Doctor doctor:DoctorList) {
				if(doctor.getDoctorName().equalsIgnoreCase(doctorName))
					return true;
					
				}
			}
		
		return false;
	
}
	public boolean searchHospitalBySpeciality(String hospitalName,String hospitalSpeciality) {
		if(this.hospital1.getHospitalName().equalsIgnoreCase(hospitalName)) {
			String[] specialities = hospital1.getHospitalSpecialities();
			for(String s:specialities)
			{
				if(s.equalsIgnoreCase(hospitalSpeciality))
				
					return true;	
			
			}
				
}
		return false;
	}
	public boolean bookBed(String hospitalName)
	{
		boolean availability = false;
		if(this.hospital1.getHospitalName().equalsIgnoreCase(hospitalName)) {
		boolean[] bedList = hospital1.getBeds();
		for (int i = 0; i < bedList.length; i++) {
			boolean[] bedsAvailable = hospital1.getBeds();
			for(Boolean b:bedsAvailable) {
				if(b) {
					b=!b;
					availability = true;
					break;
				}
			}
			
		}
		
}
		return false;
		
}
}
