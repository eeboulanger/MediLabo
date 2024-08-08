package com.medilabo.riskevaluator.service;

import com.medilabo.riskevaluator.TriggerList;
import com.medilabo.riskevaluator.beans.MedicalRecordBean;
import com.medilabo.riskevaluator.proxies.MicroserviceMedicalRecordProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskEvaluationService implements IRiskEvaluationService {

    @Autowired
    MicroserviceMedicalRecordProxy medicalRecordProxy;

    @Override
    public String getRiskLevel(int patientId) {
        String records = getMedicalRecords(patientId);
        int count = countTriggers(records);
        return "";
    }

    /**
     * Get all medical records for patient and return notes as one string
     *
     * @param patientId
     * @return
     */
    public String getMedicalRecords(int patientId) {
        StringBuilder notes = new StringBuilder();
        List<MedicalRecordBean> records = medicalRecordProxy.getMedicalRecords(patientId);

        for (MedicalRecordBean bean : records) {
            notes.append(bean.getNote()).append(" ");
        }
        return notes.toString();
    }

    /**
     * Count occurrences of triggers in notes
     * @return the number of unique triggers in notes. A trigger is only counted for once
     */
    public int countTriggers(String notes) {
        int triggers = 0;

        //Count number of triggers
        for (String trigger : TriggerList.TRIGGERS) {
            if (notes.toLowerCase().contains(trigger.toLowerCase())) {
                triggers++;
            }
        }
        return triggers;
    }
}
