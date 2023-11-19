package com.example.template;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, 
                         ids = "com.example:products:+:stubs:8090")
@ActiveProfiles("test")                      
public class ProductContractTest {

   @Autowired
   MockMvc mockMvc;

    @Test
    public void getProduct_stub_test() throws Exception {

        MvcResult result = mockMvc
        .perform(MockMvcRequestBuilders.get("/order/validateProduct/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

        String responseString = result.getResponse().getContentAsString();
        DocumentContext parsedJson = JsonPath.parse(responseString);
        // and:
        Assertions.assertThat(parsedJson.read("$.id", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.name", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.price", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.stock", String.class)).matches("[\\S\\s]+");
        Assertions.assertThat(parsedJson.read("$.imageUrl", String.class)).matches("[\\S\\s]+");
    }

}