package com.medilabo.medicalrecords.repository;

import com.medilabo.medicalrecords.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    List<MedicalRecord> getMedicalRecordByPatientId(String patId);
}
