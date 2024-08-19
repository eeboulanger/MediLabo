package com.medilabo.riskevaluator.service;

import com.medilabo.riskevaluator.RiskLevel;
import com.medilabo.riskevaluator.TriggerList;
import com.medilabo.riskevaluator.beans.MedicalRecordBean;
import com.medilabo.riskevaluator.beans.PatientBean;
import com.medilabo.riskevaluator.proxies.MicroserviceMedicalRecordProxy;
import com.medilabo.riskevaluator.proxies.MicroservicePatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RiskEvaluationService implements IRiskEvaluationService {

    private final Logger logger = LoggerFactory.getLogger(RiskEvaluationService.class);

    private final MicroserviceMedicalRecordProxy medicalRecordProxy;
    private final MicroservicePatientProxy patientProxy;

    @Autowired
    public RiskEvaluationService(MicroserviceMedicalRecordProxy medicalRecordProxy, MicroservicePatientProxy patientProxy) {
        this.medicalRecordProxy = medicalRecordProxy;
        this.patientProxy = patientProxy;
    }

    @Override
    public String getRiskLevel(int patientId) {
        String records = getMedicalRecords(patientId);
        int count = countTriggers(records);

        PatientBean patient = getPatient(patientId);
        int age = calculateAge(patient);

        RiskLevel riskLevel = evaluate(count, age, patient.getGender());
        logger.info("Patient id: {}, Risk level: {}", patientId, riskLevel.name());
        return riskLevel.name();
    }

    /**
     * Get all medical records for patient and return notes as one string
     */
    public String getMedicalRecords(int patientId) {
        logger.debug("Getting medical records for patient {}", patientId);
        StringBuilder notes = new StringBuilder();
        List<MedicalRecordBean> records = medicalRecordProxy.getMedicalRecords(patientId);
        logger.debug("Found {} medical records", records.size());

        for (MedicalRecordBean bean : records) {
            notes.append(bean.getNote()).append(" ");
        }
        return notes.toString();
    }

    /**
     * Count occurrences of triggers in notes
     *
     * @return the number of unique triggers in notes. A trigger is only counted for once
     */
    public int countTriggers(String notes) {
        int triggers = 0;
        logger.debug("Counting triggers for {}", notes);
        //Count number of triggers
        for (String trigger : TriggerList.TRIGGERS) {
            if (notes.toLowerCase().contains(trigger.toLowerCase())) {
                System.out.println(trigger);
                triggers++;
            }
        }
        logger.debug("Found {} triggers", triggers);
        return triggers;
    }

    /**
     * If no patient found, the exception will be caught by the global exception handler
     */
    private PatientBean getPatient(int patientId) {
        logger.debug("Getting patient {}", patientId);
        return patientProxy.getPatientById(patientId); //Get patient
    }

    /**
     * Calculate age based on patient's birthdate
     */
    public int calculateAge(PatientBean patient) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(patient.getBirthdate(), formatter); //Get birthdate

        LocalDate now = LocalDate.now(); //Get current date

        Period period = Period.between(birthDate, now); //Get period of time between
        logger.debug("Calculating age for patient {} age: {}", patient, period.getYears());
        return period.getYears(); //return in years
    }

    /**
     * Evaluates risk level for diabetes based on number of triggers, age and gender
     */
    public RiskLevel evaluate(int triggers, int age, String gender) {
        logger.debug("Evaluating risk for patient with triggers {} age {} gender {}", triggers, age, gender);
        if (triggers == 0) {
            return RiskLevel.None;
        } else {

            if (gender.equals("M") && age < 30) {
                if (triggers >= 5) {
                    return RiskLevel.EarlyOnset;
                } else if (triggers == 3) {
                    return RiskLevel.InDanger;
                }
            }

            if (gender.equals("F") && age < 30) {
                if (triggers == 4) {
                    return RiskLevel.InDanger;
                } else if (triggers >= 7) {
                    return RiskLevel.EarlyOnset;
                }
            }
            if (age > 30) {
                if (triggers > 1 && triggers < 6) {
                    return RiskLevel.Borderline;
                } else if (triggers == 6 || triggers == 7) {
                    return RiskLevel.InDanger;
                } else if (triggers >= 8) {
                    return RiskLevel.EarlyOnset;
                }
            }
        }
        return RiskLevel.None;
    }
}
