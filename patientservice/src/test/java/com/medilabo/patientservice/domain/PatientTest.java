package com.medilabo.patientservice.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientTest {

    @Test
    public void getters_and_settersTest(){
        String lastName= "Doe";
        String firstName = "Joe";
        String birthDate = "1950-01-01";
        String gender = "M";
        String phoneNumber = "000-111-222";
        String address= "1 brooklyn street";

        Patient patient = new Patient();
        patient.setId(1);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setGender(gender);
        patient.setBirthdate(birthDate);
        patient.setPhoneNumber(phoneNumber);
        patient.setAddress(address);

        assertEquals(1, patient.getId());
        assertEquals(lastName, patient.getLastName());
        assertEquals(firstName, patient.getFirstName());
        assertEquals(birthDate, patient.getBirthdate());
        assertEquals(gender, patient.getGender());
        assertEquals(address, patient.getAddress());
        assertEquals(phoneNumber, patient.getPhoneNumber());

    }
}