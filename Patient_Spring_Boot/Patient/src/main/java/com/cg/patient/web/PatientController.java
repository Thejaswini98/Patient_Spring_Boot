package com.cg.patient.web;

import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.patient.domain.Patient;
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
	
	@PostMapping("")
	public ResponseEntity<?> createNewPatient(@Valid @RequestBody Patient patient, BindingResult result)
	{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) 
			return errorMap;
		Patient newPatient = patientService.saveOrUpdate(patient);
		return new ResponseEntity<Patient>(newPatient, HttpStatus.CREATED);
	}
	
	@GetMapping("/{patientIdentifier}")
	public ResponseEntity<?> getPatientById(@PathVariable String patientIdentifier){
		return new ResponseEntity<Patient>( patientService.findPatientByPatientIdentifier(patientIdentifier),HttpStatus.OK);
	}

	@RequestMapping(value = "/searchHospitalByBed", method = RequestMethod.POST)
	private ResponseEntity<?> searchByBed(@RequestBody Map<String,String> hospitalByBedMap){
		boolean availability = patientService.searchHospitalByBedAvailability();
		if(availability) {
			return new ResponseEntity<String>("Hospital has beds available",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Hospital has no beds available",HttpStatus.BAD_REQUEST);
		}
		
	
	}
	@RequestMapping(value = "/searchHospitalByDoctor", method = RequestMethod.POST)
	private ResponseEntity<?> doctorAvailability(@RequestBody Map<String, String> searchHospitalMap){
		
		String doctorName = searchHospitalMap.get("doctorName");
		boolean availability = patientService.searchHospitalByDoctorAvailability(doctorName);
		if(availability) {
			return new ResponseEntity<String>(doctorName+" Doctor is available in the hospital ",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(doctorName+" Doctor is not available in the hospital " ,HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(value = "/searchHospitalBySpeciality", method = RequestMethod.POST)
	private ResponseEntity<?> hospitalBySpeciality(@RequestBody Map<String, String> searchHospitalSpecialityMap){
		
		String hospitalSpeciality = searchHospitalSpecialityMap.get("hospitalSpeciality");
		boolean availability = patientService.searchHospitalBySpeciality(hospitalSpeciality);
		if(availability) {
			return new ResponseEntity<String>(hospitalSpeciality+" Speciality is available in the hospital",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(hospitalSpeciality+" Speciality is not available in the hospital",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@RequestMapping(value = "/bookBed", method = RequestMethod.POST)
	private ResponseEntity<?> bookBed(@RequestBody Map<String,String> hospitalNameMap){
		
	
		boolean availability = patientService.bookBed();
		if(availability) {
			return new ResponseEntity<String>("Bed Successfully Booked!!",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Beds are filled! Cannot Book Bed ",HttpStatus.BAD_REQUEST);
		}
		
		
	}
	@RequestMapping(value = "/findPackageForTreatment", method = RequestMethod.POST)
	private ResponseEntity<?> packageForTreatment(@RequestBody Map<String, String> diseaseMap){
		String diseaseName = diseaseMap.get("disease");
		Set<String> packages = patientService.findPackageForTreatment(diseaseName);
		if(packages==null) {
			return new ResponseEntity<String>("NO package is available for the disease " + diseaseName,HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<Set<String>>(packages,HttpStatus.OK);
		}
		
	}
	
}
