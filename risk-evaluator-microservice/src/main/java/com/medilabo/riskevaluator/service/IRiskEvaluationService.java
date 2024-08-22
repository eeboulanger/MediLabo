package com.medilabo.riskevaluator.service;

/**
 * Handles evaluation of risk for patients
 */
public interface IRiskEvaluationService {

    String getRiskLevel(int patientId);
}
