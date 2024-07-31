package com.medilabo.patientservice.repository;

import com.medilabo.patientservice.domain.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
