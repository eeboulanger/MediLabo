package com.medilabo.gateway;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 7070)
public class MedicalRecordRoutingTests {
    @Autowired
    private WebTestClient webClient;

    @Test
    @WithMockUser(username = "user")
    public void medicalRecordsRoutingTest() {
        stubFor(get(urlEqualTo("/medicalrecords/1"))
                .willReturn(aResponse()
                        .withBody("{\"patientId\" : \"1\", \"patient\" : \"Joe\", \"note\" : \"medical note\"}")
                        .withHeader("Content-Type", "application/json")));

        webClient.get().uri("/medicalrecords/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.patientId").isEqualTo("1");
    }
}
