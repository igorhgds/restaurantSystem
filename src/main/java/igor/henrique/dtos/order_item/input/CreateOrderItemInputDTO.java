package igor.henrique.dtos.order_item.input;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderItemInputDTO(
    UUID orderId,
    UUID dishId,
    Integer quantity,
    BigDecimal unitPrice
) {
}
