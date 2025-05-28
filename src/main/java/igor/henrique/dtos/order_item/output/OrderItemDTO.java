package igor.henrique.dtos.order_item.output;

import java.math.BigDecimal;

public record OrderItemDTO(String dishName,
                           Integer quantity,
                           BigDecimal unitPrice,
                           BigDecimal totalPrice) {
}
