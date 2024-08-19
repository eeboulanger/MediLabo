package com.medilabo.medicalrecords.service;

import com.medilabo.medicalrecords.model.MedicalRecord;
import com.medilabo.medicalrecords.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MedicalRecordServiceTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    private List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void setUp() {
        medicalRecords = List.of(
                new MedicalRecord("1", "Patient 1", "Medical note for patient one"),
                new MedicalRecord("1", "Patient 1", "Medical record for patient two")
        );
    }

    @Test
    public void findMedicalRecordByPatientIdTest() {
        when(medicalRecordRepository.getMedicalRecordByPatientId("1")).thenReturn(medicalRecords);

        List<MedicalRecord> result = medicalRecordService.findByPatientId("1");

        verify(medicalRecordRepository, times(1)).getMedicalRecordByPatientId("1");
        assertEquals(medicalRecords, result);
    }

    @Test
    public void createMedicalRecordTest() {
        MedicalRecord medicalRecord= new MedicalRecord("99", "Test", "Note");
        when(medicalRecordRepository.insert(medicalRecord)).thenReturn(medicalRecord);

        MedicalRecord result = medicalRecordService.createMedicalRecord(medicalRecord);

        assertEquals(medicalRecord.getPatientId(), result.getPatientId());
        verify(medicalRecordRepository, times(1)).insert(medicalRecord);
    }
}
