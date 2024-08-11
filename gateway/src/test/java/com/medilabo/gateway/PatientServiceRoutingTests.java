package com.medilabo.gateway;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
@Disabled //Using Eureka
class PatientServiceRoutingTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    @WithMockUser(username = "user")
    public void patientsRoutingTest() {
        stubFor(get(urlEqualTo("/patients"))
                .willReturn(aResponse()
                        .withBody("{\"lastName\" : \"Doe\", \"firstName\" : \"Joe\"}")
                        .withHeader("Content-Type", "application/json")));

        webClient
                .get().uri("/patients")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.lastName").isEqualTo("Doe")
                .jsonPath("$.firstName").isEqualTo("Joe");
    }
}