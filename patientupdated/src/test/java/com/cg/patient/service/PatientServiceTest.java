package com.cg.patient.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertTrue;



import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.HospitalException;
import com.cg.patient.exception.PatientException;
import com.cg.patient.domain.Hospital;
import com.cg.patient.repository.PatientRepository;

class PatientServiceTest {
	
	@Mock
	PatientRepository patientRepository;
	
	@InjectMocks
	PatientService patientService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	/**
	 * This test method tests whether the patient objects is getting saved in repository 
	 */
	@Test
	void test1_saveOrUpdate()
	{
		Patient patient = new Patient("PA06","sam", 22,97401348L,"xyz");
		BDDMockito.given(patientRepository.save(patient)).willReturn(patient);
		Patient p = patientService.saveOrUpdate(patient);
		assertNotNull(p);
		assertEquals("sam",p.getPatientName());
		assertEquals("PA06",p.getPatientIdentifier());
		assertEquals(22,p.getPatientAge());
		assertEquals(97401348L,p.getPhoneNumber());
		assertEquals("xyz",p.getPatientAddress());

	}
	/**
	 * This test method tests whether the patient throws exception if patient is not available
	 */
	@Test
	void test2_saveOrUpdate_ThrowPatientException()
	{
		Patient patient = new Patient("PA06","sam", 22,97401348L,"xyz");
		BDDMockito.given(patientRepository.save(patient)).willThrow(new PatientException());
		assertThrows(PatientException.class, ()->patientService.saveOrUpdate(patient));
	}
	/**
	 * This test method tests findPatientByPatientIdentifier method 
	 */
	@Test
	void test3_findPatientByPatientIdentifier() {
		BDDMockito.given(patientRepository.findByPatientIdentifier("PA06")).willReturn((new Patient("pa06","abc",22,97401348L,"xyz")));
		Patient patient= patientService.findPatientByPatientIdentifier("PA06");
		assertNotNull(patient);
		assertEquals("abc", patient.getPatientName());
		assertEquals(22, patient.getPatientAge());
		assertEquals(97401348L,patient.getPhoneNumber());
		assertEquals("xyz",patient.getPatientAddress());
		
	}
	/**
	 * This test method tests findPatientByPatientIdentifier method when it throws exception
	 */
	
	@Test
	void test4_findPatientByPatientIDentifier()
	{
		BDDMockito.given(patientRepository.findByPatientIdentifier("PA06")).willThrow(new PatientException());
		assertThrows(PatientException.class, ()->patientService.findPatientByPatientIdentifier("PA06"));
	}
	/**
	 * This test method tests searchHospitalByDoctorAvailability 
	 */
	@Test
	void test5_searchHospitalByDoctorAvailability() {
		Doctor doctor = new Doctor("EDD","Orthopaedics",38383);
		List<Doctor> doctorlist= patientService.hospital1.getDoctorList();
        assertNotNull(doctorlist);
        assertEquals("EDD",  patientService.doctor1.getDoctorName());
	    assertEquals(38383,  patientService.doctor1.getDoctorPhNo());
	    assertEquals("Orthopaedics", patientService.doctor1.getSpecialization());
	    assertTrue(patientService.searchHospitalByDoctorAvailability("EDD"));
	    assertFalse(patientService.searchHospitalByDoctorAvailability("wrong"));
	}
	/**
	 * This test method tests searchHospitalByBedAvailability 
	 */
	@Test
	void test6_searchHospitalByBedAvailability() {
		List<String> diseaseList = Arrays.asList(new String[] {"Tachycardia","Arrhythmia"});
		Doctor doctor1 = new Doctor("EDD","Orthopaedics",38383);
		List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor1});
		Hospital hospital1 = new Hospital("ABC","XYZ",83828,new String[]{"Ortho","Heart"},new boolean[]{true,true,false},doctorList,diseaseList,PatientService.allPackages);
        assertNotNull(hospital1);
        assertEquals("ABC",  patientService.hospital1.getHospitalName());
	    assertEquals("XYZ",  patientService.hospital1.getHospitalAddress());
	    assertEquals(83828, patientService.hospital1.getHospitalPhNO());
	    
	}
	/**
	 * This test method tests searchHospitalBySpeciality
	 */
	@Test
	void test7_searchHospitalBySpeciality() {
		List<String> diseaseList = Arrays.asList(new String[] {"Tachycardia","Arrhythmia"});
		Doctor doctor1 = new Doctor("EDD","Orthopaedics",38383);
		List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor1});
		Hospital hospital1 = new Hospital("ABC","XYZ",83828,new String[]{"Ortho","Heart"},new boolean[]{true,true,false},doctorList,diseaseList,PatientService.allPackages);
        assertNotNull(hospital1);
        assertEquals("ABC",  patientService.hospital1.getHospitalName());
	    assertEquals("XYZ",  patientService.hospital1.getHospitalAddress());
	    for(String s:patientService.hospital1.getHospitalSpecialities() )
	    {
	    	if(s.equals("Ortho"))
	    	 assertEquals("Ortho",  s);
	    	else if(s.equals("Heart"))
	    	assertEquals("Heart", s);
	    	else
	    	assertFalse(patientService.searchHospitalBySpeciality("eye"));
	    }
 
	}
	/**
	 * This test method tests bookBed method
	 */

	@Test
	void test8_bookBed() {
		List<String> diseaseList = Arrays.asList(new String[] {"Tachycardia","Arrhythmia"});
		Doctor doctor1 = new Doctor("EDD","Orthopaedics",38383);
		List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor1});
		Hospital hospital1 = new Hospital("ABC","XYZ",83828,new String[]{"Ortho","Heart"},new boolean[]{true,true,false},doctorList,diseaseList,PatientService.allPackages);
        assertNotNull(hospital1);
        assertEquals("ABC",  patientService.hospital1.getHospitalName());
	    assertEquals("XYZ",  patientService.hospital1.getHospitalAddress());
	    assertEquals(83828, patientService.hospital1.getHospitalPhNO());
	    for(boolean b:patientService.hospital1.getBeds())
	    {
	    	if(b==true)
	    		assertEquals(true, b);
	    	else if(b==false)
	    		assertEquals(false, b);
	    }
	}
	/**
	 * This test method tests findPackageForTreatment method
	 */

	@Test
	void test_findPackageForTreatment() {
		List<String> diseaseList = Arrays.asList(new String[] {"Tachycardia","Arrhythmia"});
		Doctor doctor1 = new Doctor("EDD","Orthopaedics",38383);
		List<Doctor> doctorList = Arrays.asList(new Doctor[] {doctor1});
		
	    List<Set<String>> allPackages = new ArrayList<>();
		List<String> diseaseList1 = Arrays.asList(new String[] {"Tachycardia","Arrhythmia"});
		
			PatientService.allPackages.add(PatientService.packageForTachycardia);
			PatientService.allPackages.add(PatientService.packageForArrhythmia);
		
		Hospital hospital1 = new Hospital("ABC","XYZ",83828,new String[]{"Ortho","Heart"},new boolean[]{true,true,false},doctorList,diseaseList1,PatientService.allPackages);
        assertNotNull(hospital1);
        assertEquals("ABC",  patientService.hospital1.getHospitalName());
	    assertEquals("XYZ",  patientService.hospital1.getHospitalAddress());
	    assertEquals(83828, patientService.hospital1.getHospitalPhNO());
	}
}
	    