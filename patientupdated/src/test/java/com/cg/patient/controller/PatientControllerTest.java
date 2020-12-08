package com.cg.patient.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.patient.domain.Doctor;
import com.cg.patient.domain.Hospital;
import com.cg.patient.domain.Patient;
import com.cg.patient.exception.PatientException;
import com.cg.patient.service.MapValidationErrorService;
import com.cg.patient.service.PatientService;
import com.cg.patient.web.PatientController;
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientController.class)
class PatientControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	PatientService patientService;
	
	@MockBean
	MapValidationErrorService mapValidationErrorService;

	@InjectMocks
	PatientController patientController;
	
	@Test
	void test1_getPatientById() throws Exception
	{
		BDDMockito.given(patientService.findPatientByPatientIdentifier("PA06")).willReturn(new Patient("PA06","abc",22,87307L,"xyz"));
		mockMvc.perform(get("/api/patients/PA06"))
		.andExpect(status().isOk())
		//.andExpect(jsonPath("$").isMap())
		.andExpect(jsonPath("patientName").value("abc"))
		.andExpect(jsonPath("patientIdentifier").value("PA06"))
		.andExpect(jsonPath("patientAge").value(22))
		.andExpect(jsonPath("phoneNumber").value(87307L));
		
	}
	@Test
	void test2_getPatientById_ReturnsPatient() throws Exception
	{
		BDDMockito.given(patientService.findPatientByPatientIdentifier("PA06")).willThrow(new PatientException());
		mockMvc.perform(get("/api/patients/PA06"))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void test3_searchHospitalByBed_returnsTrueIfFound() throws Exception
	{
		when(patientService.searchHospitalByBedAvailability()).thenReturn(true);
		mockMvc.perform(post("/api/patients/searchHospitalByBed"))
		.andExpect(status().isBadRequest());
	}
	@Test
	void test4_searchHospitalByBed_returnsFalseIfNotFound() throws Exception
	{
		when(patientService.searchHospitalByBedAvailability()).thenReturn(false);
		mockMvc.perform(get("/api/patients/searchHospitalByBed"))
		.andExpect(status().isOk());
	}
	@Test
	void test5_searchHospitalByDoctor_ReturnsTrueIfDoctorFound() throws Exception
	{
		when(patientService.searchHospitalByDoctorAvailability("EDD")).thenReturn(true);
		mockMvc.perform(get("/api/patients/searchHospitalByDoctor"))
		.andExpect(status().isOk());
	
	}
	@Test
	void test6_searchHospitalByDoctor_ReturnsFalseIfDoctorNotFound() throws Exception
	{
		when(patientService.searchHospitalByDoctorAvailability("xyz")).thenReturn(false);
		mockMvc.perform(post("/api/patients/searchHospitalByDoctor"))
		.andExpect(status().isBadRequest());
	
	}
	@Test
	void test7_searchHospitalBySpeciality_ReturnsTrueIfSpecialityFound() throws Exception
	{
		when(patientService.searchHospitalBySpeciality("Ortho")).thenReturn(true);
		mockMvc.perform(get("/api/patients/searchHospitalBySpeciality"))
		.andExpect(status().isOk());
	}
	@Test
	void test8_searchHospitalBySpeciality__ReturnsTrueIfSpecialityFound() throws Exception
	{
		when(patientService.searchHospitalBySpeciality("Heart")).thenReturn(true);
		mockMvc.perform(get("/api/patients/searchHospitalBySpeciality"))
		.andExpect(status().isOk());
	}
	@Test
	void test9_searchHospitalBySpeciality__ReturnsFalseIfSpecialityNotFound() throws Exception
	{
		when(patientService.searchHospitalBySpeciality("Wrong")).thenReturn(false);
		mockMvc.perform(post("/api/patients/searchHospitalBySpeciality"))
		.andExpect(status().isBadRequest());
	}
	@Test
	void test10_bookBed_ReturnsTrueIfBooked() throws Exception
	{
		when(patientService.bookBed()).thenReturn(true);
		mockMvc.perform(get("/api/patients/bookBed"))
		.andExpect(status().isOk());
	}
	
}