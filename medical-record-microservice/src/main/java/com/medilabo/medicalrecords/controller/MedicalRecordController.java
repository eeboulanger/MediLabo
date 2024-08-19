package com.medilabo.medicalrecords.controller;

import com.medilabo.medicalrecords.model.MedicalRecord;
import com.medilabo.medicalrecords.service.IMedicalRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Fetch medical records for patient by patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found records for patient",
                    content = {
                            @Content(mediaType = "application/json")})
    })
    public ResponseEntity<List<MedicalRecord>> getMedicalRecords(@PathVariable String patientId) {
        return ResponseEntity.ok(medicalRecordService.findByPatientId(patientId));
    }

    @PostMapping
    @Operation(summary = "Adds new medical record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added new record",
                    content = {
                            @Content(mediaType = "application/json")})
    })
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.createMedicalRecord(medicalRecord));
    }
}
