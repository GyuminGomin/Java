package labshopmonolith.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DecreaseStockCommand {

    private String qty;
}
