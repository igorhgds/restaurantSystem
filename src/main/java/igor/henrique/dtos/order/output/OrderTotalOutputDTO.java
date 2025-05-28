package igor.henrique.dtos.order.output;

import java.math.BigDecimal;

public record OrderTotalOutputDTO(
        Long orderId,
        BigDecimal totalAmount
) {
}
