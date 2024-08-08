package com.medilabo.riskevaluator;

import com.medilabo.riskevaluator.beans.MedicalRecordBean;
import com.medilabo.riskevaluator.proxies.MicroserviceMedicalRecordProxy;
import com.medilabo.riskevaluator.service.RiskEvaluationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RiskEvaluationServiceTest {

    @Mock
    private MicroserviceMedicalRecordProxy medicalRecordProxy;

    @InjectMocks
    private final RiskEvaluationService riskEvaluationService = new RiskEvaluationService();

    @Test
    public void countTriggers() {
        //Given medical record note contains all triggers once
        String note = TriggerList.TRIGGERS.toString();
        System.out.println(note);

        //When counting occurrences of triggers in note
        int result = riskEvaluationService.countTriggers(note);

        //Then count should be equal to number of triggers in the list
        assertEquals(TriggerList.TRIGGERS.size(), result);

        //Given trigger occurs twice
        String trigger = TriggerList.TRIGGERS.getFirst();
        String noteWithOneTriggerTwice = trigger + " " + trigger;

        //When counting occurrences
        result = riskEvaluationService.countTriggers(noteWithOneTriggerTwice);

        //Then only count once
        assertEquals(1, result);
    }

    @Test
    public void getMedicalRecordsTest() {
        //Given there are two medical records for a patient
        List<MedicalRecordBean> medicalRecords = List.of(
                new MedicalRecordBean("1", "Test", "First note"),
                new MedicalRecordBean("1", "Test", "Second note")
        );
        when(medicalRecordProxy.getMedicalRecords(1)).thenReturn(medicalRecords);

        //When get all medical records
        String result = riskEvaluationService.getMedicalRecords(1);

        //Then concat notes
        String expectedResult = medicalRecords.getFirst().getNote() + " " + medicalRecords.get(1).getNote() + " ";

        assertEquals(expectedResult, result);
    }
}
