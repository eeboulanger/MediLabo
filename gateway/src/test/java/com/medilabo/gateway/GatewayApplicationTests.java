package com.medilabo.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8081)
class GatewayApplicationTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	void contextLoads() {
	}
	@Test
	public void patientsRoutingTest(){
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