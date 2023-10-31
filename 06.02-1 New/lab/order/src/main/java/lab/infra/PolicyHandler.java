package lab.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import lab.config.kafka.KafkaProcessor;
import lab.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderListAdded'"
    )
    public void wheneverOrderListAdded_UpdateDeliveryStatus(
        @Payload OrderListAdded orderListAdded
    ) {
        OrderListAdded event = orderListAdded;
        System.out.println(
            "\n\n##### listener UpdateDeliveryStatus : " +
            orderListAdded +
            "\n\n"
        );

        // Sample Logic //
        Order.updateDeliveryStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
