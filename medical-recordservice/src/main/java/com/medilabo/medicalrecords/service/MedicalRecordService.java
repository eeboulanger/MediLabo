package com.medilabo.medicalrecords.service;

import com.medilabo.medicalrecords.model.MedicalRecord;
import com.medilabo.medicalrecords.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public List<MedicalRecord> findByPatientId(String patientId) {
        return medicalRecordRepository.getMedicalRecordByPatientId(patientId);
    }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.insert(medicalRecord);
    }
}
