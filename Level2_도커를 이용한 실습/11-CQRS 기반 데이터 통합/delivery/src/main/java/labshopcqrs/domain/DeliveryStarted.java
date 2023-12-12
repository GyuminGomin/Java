package labshopcqrs.domain;

import labshopcqrs.domain.*;
import labshopcqrs.infra.AbstractEvent;
import java.util.*;
import lombok.*;
import java.time.LocalDate;


@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private String address;
    private String customerId;
    private Integer quantity;
    private Long orderId;

    public DeliveryStarted(Object aggregate){
        super(aggregate);
    }
    public DeliveryStarted(){
        super();
    }
}
