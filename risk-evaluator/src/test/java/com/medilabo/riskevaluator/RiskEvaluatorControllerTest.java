package com.medilabo.riskevaluator;

import com.medilabo.riskevaluator.controller.RiskEvaluatorController;
import com.medilabo.riskevaluator.service.IRiskEvaluationService;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RiskEvaluatorController.class)
public class RiskEvaluatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IRiskEvaluationService riskEvaluationService;

    @Test
    public void testRiskEvaluator() throws Exception {
        when(riskEvaluationService.getRiskLevel(1)).thenReturn(RiskLevel.None.name());

        mvc.perform(get("/risk-evaluator/{patientId}", 1))
                .andExpect(status().is2xxSuccessful());

        verify(riskEvaluationService, times(1)).getRiskLevel(1);
    }

    @Test
    public void testRiskEvaluatorFails() throws Exception {
        //Given no patient found
        Request request = Mockito.mock(Request.class);
        when(riskEvaluationService.getRiskLevel(-1))
                .thenThrow(new FeignException.NotFound("", request, null, null));

        //When evaluate risk
        //Then return http status not found
        mvc.perform(get("/risk-evaluator/{patientId}", -1))
                .andExpect(status().isNotFound());

        verify(riskEvaluationService, times(1)).getRiskLevel(-1);
    }
}
