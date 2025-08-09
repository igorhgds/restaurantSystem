package igor.henrique.dtos.order.output;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderTotalOutputDTO(
        UUID orderId,
        BigDecimal totalAmount
) {
}
