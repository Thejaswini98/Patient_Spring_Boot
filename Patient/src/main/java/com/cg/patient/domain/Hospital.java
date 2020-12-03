package com.cg.patient.domain;

import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Hospital {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long hospitalId;
	private String hospitalName;
	private String hospitalAddress;
	private long hospitalPhNO;
	private String[] hospitalSpecialities;
	private static boolean[] beds;
	

	private List<Doctor> doctorList;
	/**
	 * Create an instance of hospital with given parameter
	 * @param hospitalName
	 * @param hospitalAddress
	 * @param hospitalPhNO
	 */
	public Hospital(String hospitalName, String hospitalAddress, long hospitalPhNO, String[] hospitalSpecialities,boolean[] beds,List<Doctor> doctorList) {
		super();
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.hospitalPhNO = hospitalPhNO;
		this.hospitalSpecialities = hospitalSpecialities;
		this.doctorList = doctorList;
		this.beds = beds;
	}
	public Hospital() {
		super();
	}
	/**
	 * return hospital specialty
	 */
	public String[] getHospitalSpecialities() {
		return hospitalSpecialities;
	}
	/**
	 * set the hospital speciality
	 * @param hospitalSpeciality
	 */
	public void setHospitalSpeciality(String[] hospitalSpecialities) {
		this.hospitalSpecialities = hospitalSpecialities;
	}
	
//	/**
//	 * @return the hospital ID
//	 */
//	public long getHospitalId() {
//		return hospitalId;
//	}
//	/**
//	 * Set the hospital ID
//	 * @param hospitalId
//	 */
//	public void setHospitalId(long hospitalId) {
//		this.hospitalId = hospitalId;
//	}
	/**
	 * @return hospital name 
	 */
	public String getHospitalName() {
		return hospitalName;
	}
	/**
	 * Set the name of the hospital
	 * @param hospitalName
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	/**
	 * @return the hospital address
	 */
	public String getHospitalAddress() {
		return hospitalAddress;
	}
	/**
	 * Set the hospital address
	 * @param hospitalAddress
	 */
	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	/**
	 * @return the hospital phone number 
	 */
	public long getHospitalPhNO() {
		return hospitalPhNO;
	}
	public void setHospitalPhNO(long hospitalPhNO) {
		this.hospitalPhNO = hospitalPhNO;
	}
	
	public boolean[] getBeds() {
		return beds;
	}
	public void setBeds(boolean[] beds) {
		this.beds = beds;
	}
	public void setHospitalSpecialities(String[] hospitalSpecialities) {
		this.hospitalSpecialities = hospitalSpecialities;
	}
	
	
	public List<Doctor> getDoctorList() {
		return doctorList;
	}
	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}
	@Override
	public String toString() {
		return "Hospital [hospitalName=" + hospitalName + ", hospitalAddress=" + hospitalAddress + ", hospitalPhNO="
				+ hospitalPhNO + ", hospitalSpecialities=" + Arrays.toString(hospitalSpecialities) + ", beds="
				+ Arrays.toString(beds) + ", doctorList=" + doctorList + "]";
	}
	
	
	
	
}
