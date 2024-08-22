package com.medilabo.medicalrecords.service;

import com.medilabo.medicalrecords.model.MedicalRecord;

import java.util.List;

/**
 * Any class handling operations on medical records
 */

public interface IMedicalRecordService {
    List<MedicalRecord> findByPatientId(String patientId);

    MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);
}
