package com.pivovarit.descriptions;

import com.pivovarit.Application;
import com.pivovarit.DescriptionsRestController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMessageVerifier
class ContractTestBase {

    @Autowired
    MovieDescriptionsFacade descriptionsFacade;

    @Autowired
    DescriptionsRestController controller;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders.standaloneSetup(controller));
    }
}
