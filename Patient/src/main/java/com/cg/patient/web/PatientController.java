package com.cg.patient.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.patient.service.MapValidationErrorService;
import com.cg.patient.service.PatientService;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@GetMapping("/{hospitalName}")
	private ResponseEntity<?> bedAvailability(@PathVariable String hospitalName){
		boolean availability = patientService.searchHospitalByBedAvailability(hospitalName);
		if(availability) {
			return new ResponseEntity<String>("Beds are available in the hospital",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Beds are not available in the hospital",HttpStatus.BAD_REQUEST);
		}
		
	
	}
	@RequestMapping(value = "/searchHospitalByDoctor", method = RequestMethod.POST)
	private ResponseEntity<?> doctorAvailability(@RequestBody Map<String, String> searchHospitalMap){
		String hospitalName = searchHospitalMap.get("hospitalName");
		String doctorName = searchHospitalMap.get("doctorName");
		boolean availability = patientService.searchHospitalByDoctorAvailability(hospitalName,doctorName);
		if(availability) {
			return new ResponseEntity<String>("Doctor is available in the hospital",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Doctor is not available in the hospital",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(value = "/searchHospitalBySpeciality", method = RequestMethod.POST)
	private ResponseEntity<?> hospitalBySpeciality(@RequestBody Map<String, String> searchHospitalSpecialityMap){
		String hospitalName = searchHospitalSpecialityMap.get("hospitalName");
		String hospitalSpeciality = searchHospitalSpecialityMap.get("hospitalSpeciality");
		boolean availability = patientService.searchHospitalBySpeciality(hospitalName,hospitalSpeciality);
		if(availability) {
			return new ResponseEntity<String>(hospitalSpeciality+" Speciality is available in the hospital",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(hospitalSpeciality+" Speciality is not available in the hospital",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(value = "/bookBed", method = RequestMethod.POST)
	private ResponseEntity<?> bookBed(@RequestBody String hospitalName){
		
	
		boolean availability = patientService.bookBed(hospitalName);
		if(availability) {
			return new ResponseEntity<String>("Bed Successfully Booked",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Beds are filled! Cannot Book Bed",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	
	
}
