package com.medilabo.patientservice.controller;

import com.medilabo.patientservice.domain.Patient;
import com.medilabo.patientservice.service.IPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API for patient data
 */

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Get all patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found patients",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class))})
    })
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @Operation(summary = "Get a patient by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the patient",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class))}),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable int id) {
        return ResponseEntity.ok(patientService.getPatient(id));
    }

    @Operation(summary = "Add a new patient")
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody @Valid Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @Operation(summary = "Update a patient by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found and updated the patient",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class))}),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody @Valid Patient patient) {
        return ResponseEntity.ok(patientService.updatePatient(patient, id));
    }
}