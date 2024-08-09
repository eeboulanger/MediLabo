package com.medilabo.riskevaluator;

import com.medilabo.riskevaluator.beans.MedicalRecordBean;
import com.medilabo.riskevaluator.beans.PatientBean;
import com.medilabo.riskevaluator.proxies.MicroserviceMedicalRecordProxy;
import com.medilabo.riskevaluator.proxies.MicroservicePatientProxy;
import com.medilabo.riskevaluator.service.RiskEvaluationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RiskEvaluationServiceTest {

    @Mock
    private MicroserviceMedicalRecordProxy medicalRecordProxy;
    @Mock
    private MicroservicePatientProxy patientProxy;

    @InjectMocks
    private RiskEvaluationService riskEvaluationService;

    @Test
    public void countTriggers() {
        //Given medical record note contains all triggers once
        String note = TriggerList.TRIGGERS.toString();

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

    @Test
    public void calculateAgeTest() {
        //Given patient born 1950
        PatientBean patient = new PatientBean();
        patient.setBirthdate("1950-01-01");

        //When calculating age
        int result = riskEvaluationService.calculateAge(patient);

        //Then return age based on current date
        LocalDate now = LocalDate.now();
        LocalDate patientBirthDate = LocalDate.parse(patient.getBirthdate());
        Period expectedPeriod = Period.between(patientBirthDate, now);

        assertEquals(expectedPeriod.getYears(), result);
    }

    @Test
    public void evaluateRiskTest() {
        //Given no risk
        RiskLevel result = riskEvaluationService.evaluate(0, 40, "F");
        assertEquals(RiskLevel.None, result);

        //Given Borderline
        result = riskEvaluationService.evaluate(5, 40, "F");
        assertEquals(RiskLevel.Borderline, result);

        //Given In danger
        result = riskEvaluationService.evaluate(3, 20, "M");
        assertEquals(RiskLevel.InDanger, result);

        result = riskEvaluationService.evaluate(4, 20, "F");
        assertEquals(RiskLevel.InDanger, result);

        result = riskEvaluationService.evaluate(6, 40, "F");
        assertEquals(RiskLevel.InDanger, result);

        //Given Early onset
        result = riskEvaluationService.evaluate(5, 20, "M");
        assertEquals(RiskLevel.EarlyOnset, result);

        result = riskEvaluationService.evaluate(7, 20, "F");
        assertEquals(RiskLevel.EarlyOnset, result);

        result = riskEvaluationService.evaluate(8, 40, "M");
        assertEquals(RiskLevel.EarlyOnset, result);
    }

    /**
     * PatId Risque attendu
     * 1 None
     * 2 Borderline
     * 3 InDanger
     * 4 EarlyOnset
     */
    @Test
    public void getRiskLevelTest() {

        List<PatientBean> patients = List.of(
                new PatientBean(1, "TestNone", "Test", "1966-12-31", "F", "1 Brookside St", "100-222-3333"),
                new PatientBean(2, "TestBorderline", "Test", "1945-06-24", "M", "2 High St", "200-333-4444"),
                new PatientBean(3, "TestInDanger", "Test", "2004-06-18", "M", "3 Club Road", "300-444-5555"),
                new PatientBean(4, "TestEarlyOnset", "Test", "2002-06-28", "F", "4 Valley Dr", "400-555-6666")
        );

        List<MedicalRecordBean> records = List.of(
                new MedicalRecordBean("1", "TestNone", "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"),
                new MedicalRecordBean("2", "TestBorderline", "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"),
                new MedicalRecordBean("2", "TestBorderline", "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"),
                new MedicalRecordBean("3", "TestInDanger", "Le patient déclare qu'il fume depuis peu"),
                new MedicalRecordBean("3", "TestInDanger", "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"),
                new MedicalRecordBean("4", "TestEarlyOnset", "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"),
                new MedicalRecordBean("4", "TestEarlyOnset", "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"),
                new MedicalRecordBean("4", "TestEarlyOnset", "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"),
                new MedicalRecordBean("4", "TestEarlyOnset", "Taille, Poids, Cholestérol, Vertige et Réaction")
        );

        //Given no risk
        when(patientProxy.getPatientById(1)).thenReturn(patients.getFirst());
        when(medicalRecordProxy.getMedicalRecords(1)).thenReturn(List.of(records.getFirst()));

        String riskLevel = riskEvaluationService.getRiskLevel(patients.getFirst().getId());
        assertEquals("None", riskLevel);

        //Given Border line
        when(medicalRecordProxy.getMedicalRecords(2)).thenReturn(List.of(records.get(1), records.get(2)));
        when(patientProxy.getPatientById(2)).thenReturn(patients.get(1));

        riskLevel = riskEvaluationService.getRiskLevel(patients.get(1).getId());
        assertEquals("Borderline", riskLevel);

        //Given in danger
        when(medicalRecordProxy.getMedicalRecords(3)).thenReturn(List.of(records.get(3), records.get(4)));
        when(patientProxy.getPatientById(3)).thenReturn(patients.get(2));

        riskLevel = riskEvaluationService.getRiskLevel(patients.get(2).getId());
        assertEquals("InDanger", riskLevel);

        //Given Early onset
        when(medicalRecordProxy.getMedicalRecords(4)).thenReturn(List.of(
                records.get(5),
                records.get(6),
                records.get(7),
                records.get(8)));
        when(patientProxy.getPatientById(4)).thenReturn(patients.get(3));

        riskLevel = riskEvaluationService.getRiskLevel(patients.get(3).getId());
        assertEquals("EarlyOnset", riskLevel);
    }
}
