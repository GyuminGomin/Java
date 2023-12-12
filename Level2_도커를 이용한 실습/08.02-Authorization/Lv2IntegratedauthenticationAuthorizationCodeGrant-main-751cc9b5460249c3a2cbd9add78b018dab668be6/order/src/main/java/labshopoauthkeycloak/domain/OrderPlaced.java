package labshopoauthkeycloak.domain;

import java.time.LocalDate;
import java.util.*;
import labshopoauthkeycloak.domain.*;
import labshopoauthkeycloak.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String productId;
    private Integer qty;
    private String customerId;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
