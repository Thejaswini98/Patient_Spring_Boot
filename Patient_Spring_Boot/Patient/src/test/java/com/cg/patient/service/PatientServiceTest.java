package com.cg.patient.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.patient.domain.Patient;
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
	
	@Test
	void test_findPatientByPatientIdentifier() {
		BDDMockito.given(patientRepository.findByPatientIdentifier("PA01")).willReturn((new Patient("abc",22,97401348L,"xyz")));
		Patient patient= patientService.findPatientByPatientIdentifier("PA01");
		assertNotNull(patient);
		assertEquals("abc", patient.getPatientName());
		assertNull(patient.getPatientIdentifier());
		assertEquals(22, patient.getPatientAge());
		assertEquals(97401348L,patient.getPhoneNumber());
		assertEquals("xyz",patient.getPatientAddress());
		
	}
	

}