package lab.domain;

import java.time.LocalDate;
import java.util.*;
import lab.domain.*;
import lab.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String prouductId;
    private Integer qty;
    private String customerId;
    private String amount;
    private String address;
    private String status;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
