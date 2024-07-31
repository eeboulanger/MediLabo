package com.medilabo.patientservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.patientservice.domain.Patient;
import com.medilabo.patientservice.service.IPatientService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IPatientService patientService;

    private List<Patient> patientList;

    @BeforeEach
    public void setUp() {
        //Given two patients
        patientList = List.of(
                new Patient("Doe", "Joe", "1950-01-01", "M", null, null),
                new Patient("Doe", "Jane", "1955-01-01", "F", null, null));
    }

    @Test
    @DisplayName("Get patients should show patients info")
    public void getAllPatientsTest() throws Exception {
        when(patientService.getAllPatients()).thenReturn(patientList);

        mockMvc.perform(get("/patients"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0].firstName").value(patientList.get(0).getFirstName()))
                .andExpect(jsonPath("$.[1].firstName").value(patientList.get(1).getFirstName()));

        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    @DisplayName("Get patient by id should return patient info")
    public void getPatientTest() throws Exception {
        when(patientService.getPatient(1)).thenReturn(patientList.get(1));

        mockMvc.perform(get("/patients/{id}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value(patientList.get(1).getFirstName()));

        verify(patientService, times(1)).getPatient(1);
    }

    @Test
    @DisplayName("Given no patient with id, when Get patient by id should return status not found")
    public void getPatientFailsTest() throws Exception {
        when(patientService.getPatient(-1)).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/patients/{id}", -1))
                .andExpect(status().isNotFound());

        verify(patientService, times(1)).getPatient(-1);
    }

    @Test
    @DisplayName("Add new patient should return ok and the created patient")
    public void addPatientTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String newPatient = mapper.writeValueAsString(patientList.getFirst());
        when(patientService.createPatient(any(Patient.class))).thenReturn(patientList.getFirst());

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPatient))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value(patientList.getFirst().getFirstName()));

        verify(patientService, times(1)).createPatient(any(Patient.class));
    }

    @Test
    @DisplayName("Add new patient when not valid arguments should return bad request")
    public void addPatientFailsTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String newPatient = mapper.writeValueAsString(new Patient()); //Empty values will fail validation

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPatient))
                .andExpect(status().isBadRequest());

        verify(patientService, never()).createPatient(any(Patient.class));
    }

    @Test
    @DisplayName("Given patient exists, when update patient then return updated patient")
    public void updatePatientTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String updatedPatient = mapper.writeValueAsString(patientList.get(1));
        when(patientService.updatePatient(any(Patient.class), eq(1))).thenReturn(patientList.get(1));

        mockMvc.perform(put("/patients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPatient))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName").value(patientList.get(1).getFirstName()));

        verify(patientService, times(1)).updatePatient(any(Patient.class), eq(1));
    }

    @Test
    @DisplayName("Given patient doesn't exist, when update patient then return status not found")
    public void updatePatientFailsTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String updatedPatient = mapper.writeValueAsString(patientList.get(1));
        when(patientService.updatePatient(any(Patient.class), eq(1))).thenThrow(EntityNotFoundException.class);

        mockMvc.perform(put("/patients/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPatient))
                .andExpect(status().isNotFound());

        verify(patientService, times(1)).updatePatient(any(Patient.class), eq(1));
    }
}