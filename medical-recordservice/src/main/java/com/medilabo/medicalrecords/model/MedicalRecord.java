package com.medilabo.medicalrecords.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "records")
public class MedicalRecord {
    @Id
    private String id;
    private String patientId;
    private String patient;
    private String note;

    public MedicalRecord(String patientId, String patient, String note) {
        this.patientId = patientId;
        this.patient = patient;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
