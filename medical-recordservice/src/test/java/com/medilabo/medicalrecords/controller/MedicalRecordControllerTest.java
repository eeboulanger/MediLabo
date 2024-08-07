package com.medilabo.medicalrecords.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.medicalrecords.model.MedicalRecord;
import com.medilabo.medicalrecords.service.IMedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IMedicalRecordService medicalRecordService;

    @Test
    public void getAllMedicalRecords() throws Exception {
        List<MedicalRecord> medicalRecord = List.of(new MedicalRecord("1", "Test", "Note"));
        when(medicalRecordService.findByPatientId("1")).thenReturn(medicalRecord);

        mockMvc.perform(get("/api/medicalrecords/{patientId}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.[0].patientId").value("1"))
                .andExpect(jsonPath("$.[0].patient").value("Test"))
                .andExpect(jsonPath("$.[0].note").value("Note"));

        verify(medicalRecordService, times(1)).findByPatientId("1");
    }

    @Test
    public void createMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord("1", "Test", "Note");
        ObjectMapper mapper = new ObjectMapper();

        when(medicalRecordService.createMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);
        mockMvc.perform(post("/api/medicalrecords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(medicalRecord)))
                .andExpect(status().is2xxSuccessful());

        verify(medicalRecordService, times(1)).createMedicalRecord(any(MedicalRecord.class));
    }
}
