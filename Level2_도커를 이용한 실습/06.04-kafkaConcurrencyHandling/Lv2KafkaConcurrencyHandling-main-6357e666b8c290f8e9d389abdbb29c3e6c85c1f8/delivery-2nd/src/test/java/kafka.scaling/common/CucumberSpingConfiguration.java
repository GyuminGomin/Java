package kafka.scaling.common;

import io.cucumber.spring.CucumberContextConfiguration;
import kafka.scaling.DeliveryApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { DeliveryApplication.class })
public class CucumberSpingConfiguration {}
