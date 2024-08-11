package com.medilabo.gateway;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9090)
@Disabled //Using Eureka
public class RiskEvaluatorRoutingTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    @WithMockUser(username = "user")
    @Disabled //Using Eureka
    public void riskEvaluatorTest() throws Exception {
        stubFor(get(urlEqualTo("/risk-evaluator/1"))
                .willReturn(aResponse()
                        .withBody("None")));

        webClient.get().uri("/risk-evaluator/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class).isEqualTo("None");
    }

}
