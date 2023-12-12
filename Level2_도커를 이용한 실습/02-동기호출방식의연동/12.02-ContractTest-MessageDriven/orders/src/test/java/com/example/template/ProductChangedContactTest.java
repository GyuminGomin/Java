package com.example.template;

import com.example.template.config.kafka.KafkaProcessor;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, 
                        ids = "com.example:boot-camp-products:+:stubs")
public class ProductChangedContactTest {

    @Autowired
    StubFinder stubFinder;

    @Autowired
    private KafkaProcessor processor;

    @Autowired
    // collect messages sent by a Spring Cloud Stream application
    private MessageCollector messageCollector;

    @Test
    public void testOnMessageReceived() {
        // Set Contract Label
        stubFinder.trigger("ProductChanged");

        Message<String> received = (Message<String>) messageCollector.forChannel(processor.outboundTopic()).poll();
        System.out.println("<<<<<<< " + received.getPayload() + ">>>>>>");

        DocumentContext parsedJson = JsonPath.parse(received.getPayload());

        // Verify received message
        assertThat(parsedJson.read("$.eventType", String.class)).isEqualTo("ProductChanged");
        assertThat(parsedJson.read("$.productId", String.class)).matches("[\\S\\s]+");
        assertThat(parsedJson.read("$.productName", String.class)).matches("[\\S\\s]+");
        assertThat(parsedJson.read("$.productPrice", String.class)).matches("[\\S\\s]+");
        assertThat(parsedJson.read("$.productStock", String.class)).matches("[\\S\\s]+");
        assertThat(parsedJson.read("$.imageUrl", String.class)).matches("[\\S\\s]+");

    }
}
