package igor.henrique.dtos.order.output;

import java.math.BigDecimal;

public record OrderOutputDTO(
        Long id,
        Integer tableNumber,
        String waiterName,
        String orderStatus,
        String orderDateTime,
        Double totalPrice
) {
}
