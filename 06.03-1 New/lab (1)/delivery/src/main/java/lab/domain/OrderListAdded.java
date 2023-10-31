package lab.domain;

import java.time.LocalDate;
import java.util.*;
import lab.domain.*;
import lab.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderListAdded extends AbstractEvent {

    private Long id;
    private String orderId;
    private String customerId;
    private String address;
    private Integer quantity;
    private String status;

    public OrderListAdded(Delivery aggregate) {
        super(aggregate);
    }

    public OrderListAdded() {
        super();
    }
}
//>>> DDD / Domain Event
