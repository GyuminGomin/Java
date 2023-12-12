package kafka.cdc.external;

import lombok.Data;
import java.util.Date;
@Data
public class SyncOrder {

    private Long id;
    private String customerId;
    private Integer qty;
    private Double price;
    private Long productId;
}


