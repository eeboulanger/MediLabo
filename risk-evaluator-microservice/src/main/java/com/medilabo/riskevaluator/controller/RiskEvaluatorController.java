package com.medilabo.riskevaluator.controller;

import com.medilabo.riskevaluator.service.IRiskEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API for risk evaluation for patients
 */

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
    @Operation(summary = "Evaluate health risk for patient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found patient and evaluated risk",
                    content = {
                            @Content(mediaType = "text/plain")})
    })
    public ResponseEntity<String> evaluate(@PathVariable int patientId) {
        String riskLevel = riskEvaluationService.getRiskLevel(patientId);
        logger.info("Patient id: {} , Risklevel: {}", patientId, riskLevel);
        return ResponseEntity.ok(riskLevel);
    }
}
