package com.medilabo.patientservice;

import com.medilabo.patientservice.domain.Patient;
import com.medilabo.patientservice.repository.PatientRepository;
import com.medilabo.patientservice.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private List<Patient> patients;

    @BeforeEach
    public void setUp() {
        patients = List.of(
                new Patient("Doe", "Joe", "1950-01-01", "M", null, null),
                new Patient("Doe", "Jane", "1945-01-01", "F", null, null)
        );
    }

    @Test
    public void getAllPatientsTest() {
        when(patientRepository.findAll()).thenReturn(patients);
        List<Patient> result = patientService.getAllPatients();

        assertEquals(patients, result);
        verify(patientRepository).findAll();
    }

    @Test
    public void getPatientTest() {
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patients.getFirst()));
        Patient result = patientService.getPatient(1);

        assertEquals(patients.getFirst(), result);
        verify(patientRepository).findById(1);
    }

    @Test
    public void updatePatientTest() {
        when(patientRepository.findById(1)).thenReturn(Optional.ofNullable(patients.getFirst()));
        when(patientRepository.save(any(Patient.class))).thenReturn(patients.getFirst());
        Patient result = patientService.updatePatient(patients.getFirst(), 1);

        assertEquals(patients.getFirst(), result);
        verify(patientRepository).findById(1);
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    public void updatePatient_whenNoPatientFound_shouldThrownException() {
        when(patientRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> patientService.updatePatient(patients.getFirst(), 1));

        verify(patientRepository).findById(1);
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    public void createPatientTest() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patients.getFirst());

        Patient result = patientService.createPatient(patients.getFirst());
        assertEquals(patients.getFirst(), result);
    }
}
