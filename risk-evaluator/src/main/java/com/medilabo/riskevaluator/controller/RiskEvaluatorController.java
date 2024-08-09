package com.medilabo.riskevaluator.controller;

import com.medilabo.riskevaluator.service.IRiskEvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/risk-evaluator")
public class RiskEvaluatorController {
    private final Logger logger = LoggerFactory.getLogger(RiskEvaluatorController.class);
    private final IRiskEvaluationService riskEvaluationService;

    @Autowired
    public RiskEvaluatorController(IRiskEvaluationService riskEvaluationService) {
        this.riskEvaluationService = riskEvaluationService;
    }

    /**
     * Exceptions handled in global exception handler
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<String> evaluate(@PathVariable int patientId) {
        String riskLevel = riskEvaluationService.getRiskLevel(patientId);
        logger.info("Patient id: {} , Risklevel: {}", patientId, riskLevel);
        return ResponseEntity.ok(riskLevel);
    }
}
