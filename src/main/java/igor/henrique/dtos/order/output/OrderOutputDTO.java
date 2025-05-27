package igor.henrique.dtos.order.output;

import java.math.BigDecimal;

public record OrderOutputDTO(
        Long orderId,
        Integer tableNumber,
        String waiterName,
        String orderStatus,
        String orderDateTime,
        BigDecimal totalPrice
) {
}
