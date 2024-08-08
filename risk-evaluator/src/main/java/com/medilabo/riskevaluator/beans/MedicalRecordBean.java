package com.medilabo.riskevaluator.beans;

public class MedicalRecordBean {
    private String patientId;
    private String patient;
    private String note;

    public MedicalRecordBean() {
    }

    public MedicalRecordBean(String patientId, String patient, String note) {
        this.patientId = patientId;
        this.patient = patient;
        this.note = note;
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

    @Override
    public String toString() {
        return "MedicalRecordBean{" +
                "patientId='" + patientId + '\'' +
                ", patient='" + patient + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
