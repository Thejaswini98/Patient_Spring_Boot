package com.cg.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cg.patient.exception.HospitalException;
import com.cg.patient.exception.PatientException;
import com.cg.patient.repository.PatientRepository;
import com.cg.patient.domain.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
@Service
public class PatientService {
	
	@Autowired
	PatientRepository repository;
	
	
	
	public PatientRepository getRepository() {
		return repository;
	}

	Doctor doctor1 = new Doctor("EDD","Orthopaedics",38383);
	List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor1});
	static Set<String> packageForTachycardia =new HashSet<>( Arrays.asList(new String[] {"dhhd","dsd"}));
	static Set<String> packageForArrhythmia =new HashSet<>( Arrays.asList(new String[] {"dhd","sdsdsd"}));
	static List<Set<String>> allPackages = new ArrayList<>();
	List<String> diseaseList = Arrays.asList(new String[] {"Tachycardia","Arrhythmia"});
	
	static {
		PatientService.allPackages.add(PatientService.packageForTachycardia);
		PatientService.allPackages.add(PatientService.packageForArrhythmia);
	}
	
	Hospital hospital1 = new Hospital("ABC","XYZ",83828,new String[]{"Ortho","Heart"},new boolean[]{true,true,false},doctorList,diseaseList,PatientService.allPackages);
	
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
	
	public boolean searchHospitalByBedAvailability() {
		boolean availability = false;
		
			boolean[] bedsAvailable = hospital1.getBeds();
			for(Boolean b:bedsAvailable) {
				if(b) {
					availability = true;
					break;
				}
			}
			return availability;
		}
		
	
	public boolean searchHospitalByDoctorAvailability(String doctorName) {
		
			List<Doctor> DoctorList = hospital1.getDoctorList();
			
			for(Doctor doctor:DoctorList) {
				if(doctor.getDoctorName().equalsIgnoreCase(doctorName))
					return true;
					
				}
			return false;
			
		
		
	
}
	public boolean searchHospitalBySpeciality(String hospitalSpeciality) {
		
			String[] specialities = hospital1.getHospitalSpecialities();
			for(String s:specialities)
			{
				if(s.equalsIgnoreCase(hospitalSpeciality))
				
					return true;	
			
			}
				

		return false;
	}
	public boolean bookBed()
	{
		
				boolean[] bedList = hospital1.getBeds();
				for(int i=0;i<bedList.length;i++)
				{
					if(bedList[i]==false)
					{
						bedList[i]=true;
						return true;
					}
					
				}
					
				return false;
			}
	public Set<String> findPackageForTreatment(String disease)
	{
		Map<String,Set<String>> allPackages = hospital1.getAvailablePackageForDisease();
		if(allPackages.containsKey(disease)) {
			return allPackages.get(disease);
		}
		else {
			return null;
		}
	}
		
}

