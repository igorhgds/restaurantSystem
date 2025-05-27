package igor.henrique.dtos.order.input;

public record ListOrdersFilterInputDTO(
        Integer tableNumber,
        String waiterName,
        String orderStatus
) {
}
