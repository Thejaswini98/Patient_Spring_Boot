package com.cg.patient.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Hospital {

	private String hospitalName;
	private String hospitalAddress;
	private long hospitalPhNO;
	private String[] hospitalSpecialities;
	private static boolean[] beds;
	
	private List<Doctor> doctorList;
	private Map<String,Set<String>> availablePackageForDisease = new HashMap<>();
	/**
	 * Create an instance of hospital with given parameter
	 * @param hospitalName
	 * @param hospitalAddress
	 * @param hospitalPhNO
	 */
	public Hospital(String hospitalName, String hospitalAddress, long hospitalPhNO, String[] hospitalSpecialities,boolean[] beds,List<Doctor> doctorList,List<String> diseases,List<Set<String>> packages) {
		super();
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.hospitalPhNO = hospitalPhNO;
		this.hospitalSpecialities = hospitalSpecialities;
		this.doctorList = doctorList;
		Hospital.beds = beds;
		int index = 0;
		for(String disease:diseases) {
			if(!this.availablePackageForDisease.containsKey(disease)) {
				this.availablePackageForDisease.put(disease, packages.get(index));
			}
			else {
				for(String packageGot:packages.get(index)) {
					this.availablePackageForDisease.get(disease).add(packageGot);
				}
			}
			index += 1;
		}
	}
	public Hospital() {
		super();
	}
	
	
	
	public Map<String, Set<String>> getAvailablePackageForDisease() {
		return availablePackageForDisease;
	}
	public void setAvailablePackageForDisease(Map<String, Set<String>> availablePackageForDisease) {
		this.availablePackageForDisease = availablePackageForDisease;
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
				+ hospitalPhNO + ", hospitalSpecialities=" + Arrays.toString(hospitalSpecialities) + ", doctorList="
				+ doctorList + ", availablePackageForDisease=" + availablePackageForDisease + "]";
	}
	
	
	
	
	
}
