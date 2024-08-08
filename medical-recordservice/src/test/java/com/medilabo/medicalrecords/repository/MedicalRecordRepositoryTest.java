package com.medilabo.medicalrecords.repository;

import com.medilabo.medicalrecords.model.MedicalRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class MedicalRecordRepositoryTest {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    private List<MedicalRecord> medicalRecords;

    @BeforeEach
    public void setUp() {
        medicalRecords = List.of(
                new MedicalRecord("-1", "TestPatient", "first medical note"),
                new MedicalRecord("-1", "TestPatient", "second medical note"),
                new MedicalRecord("-1", "TestPatient", "third medical note")
        );
        medicalRecordRepository.insert(medicalRecords);
    }

    @AfterEach
    public void tearDown() {
        medicalRecords = medicalRecordRepository.getMedicalRecordByPatientId("-1");
        medicalRecordRepository.deleteAll(medicalRecords);
    }

    @Test
    public void insertMedicalRecordTest() {
        //Given a new medical record
        MedicalRecord newMedRec = new MedicalRecord("-1", "TestPatient", "medical note");

        //When saving to database
        MedicalRecord result = medicalRecordRepository.insert(newMedRec);

        //Then return medical record
        assertEquals(newMedRec, result);
    }

    @Test
    public void getAllMedicalRecordsByPatientIdTest() {
        //Given there are medical records

        //When get all records
        List<MedicalRecord> result = medicalRecordRepository.getMedicalRecordByPatientId("-1");

        //Then return the three records
        assertEquals(3, result.size());
        assertEquals("first medical note", result.get(0).getNote());
        assertEquals("second medical note", result.get(1).getNote());
        assertEquals("third medical note", result.get(2).getNote());
    }
}
