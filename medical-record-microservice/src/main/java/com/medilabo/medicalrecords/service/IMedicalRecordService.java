package com.medilabo.medicalrecords.service;

import com.medilabo.medicalrecords.model.MedicalRecord;

import java.util.List;

public interface IMedicalRecordService {
    List<MedicalRecord> findByPatientId(String patientId);

    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
}
