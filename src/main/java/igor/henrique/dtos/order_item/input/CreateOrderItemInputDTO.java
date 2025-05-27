package igor.henrique.dtos.order_item.input;

import java.math.BigDecimal;

public record CreateOrderItemInputDTO(
    Long orderId,
    Long dishId,
    Integer quantity,
    BigDecimal unitPrice
) {
}
