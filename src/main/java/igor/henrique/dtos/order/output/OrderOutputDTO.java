package igor.henrique.dtos.order.output;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderOutputDTO(
        UUID orderId,
        Integer tableNumber,
        String waiterName,
        String orderStatus,
        String orderDateTime,
        BigDecimal totalPrice
) {
}
