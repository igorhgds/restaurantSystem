package igor.henrique.dtos.order_item.output;

import java.util.UUID;

public record OrderItemOutputDTO(
    UUID orderItemId,
    UUID orderId,
    UUID dishId,
    String dishName,
    Integer quantity,
    Double unitPrice,
    String createdAt
) {
}
