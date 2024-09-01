package com.medilabo.patientservice.repository;

import com.medilabo.patientservice.domain.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;
    int numberOfPatients;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        numberOfPatients = ((List<Patient>) patientRepository.findAll()).size();
    }

    @Test
    public void patientCRUDTests() {
        //Given a number of patients in database
        //When Find All
        List<Patient> patientList = (List<Patient>) patientRepository.findAll();

        //Then return all patients in database
        assertEquals(numberOfPatients, patientList.size());

        //When save
        patient = new Patient("Doe", "Joe", "1950-01-01", "M", null, null);
        patient = patientRepository.save(patient);

        //Then number of patients should increase
        assertEquals(numberOfPatients + 1, ((List<Patient>) patientRepository.findAll()).size());

        //When Find by Id
        Optional<Patient> optional = patientRepository.findById(patient.getId());

        //Then return patient
        assertTrue(optional.isPresent());

        //When delete patient
        patientRepository.delete(patient);

        //Then number of patients should decrease
        assertEquals(numberOfPatients, ((List<Patient>) patientRepository.findAll()).size());
    }
}