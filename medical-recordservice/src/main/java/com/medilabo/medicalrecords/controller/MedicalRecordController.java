package com.medilabo.medicalrecords.controller;

import com.medilabo.medicalrecords.model.MedicalRecord;
import com.medilabo.medicalrecords.service.IMedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalrecords")
public class MedicalRecordController {

    @Autowired
    private IMedicalRecordService medicalRecordService;

    @GetMapping("/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecords(@PathVariable String patientId) {
        return ResponseEntity.ok(medicalRecordService.findByPatientId(patientId));
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.createMedicalRecord(medicalRecord));
    }
}
