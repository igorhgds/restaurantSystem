package igor.henrique.dtos.order_item.output;

public record OrderItemOutputDTO(
    Long orderItemId,
    Long orderId,
    Long dishId,
    String dishName,
    Integer quantity,
    Double unitPrice,
    String createdAt
) {
}
