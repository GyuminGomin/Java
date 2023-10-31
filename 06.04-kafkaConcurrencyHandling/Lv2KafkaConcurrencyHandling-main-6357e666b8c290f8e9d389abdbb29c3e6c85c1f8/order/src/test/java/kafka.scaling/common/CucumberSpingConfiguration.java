package kafka.scaling.common;

import io.cucumber.spring.CucumberContextConfiguration;
import kafka.scaling.OrderApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { OrderApplication.class })
public class CucumberSpingConfiguration {}
