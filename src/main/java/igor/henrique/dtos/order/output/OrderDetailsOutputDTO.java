package igor.henrique.dtos.order.output;

import igor.henrique.dtos.order_item.output.OrderItemDTO;

import java.math.BigDecimal;
import java.util.List;

public record OrderDetailsOutputDTO(Long orderId,
                                    Integer tableNumber,
                                    String waiterName,
                                    List<OrderItemDTO> items,
                                    BigDecimal totalAmount,
                                    String orderStatus,
                                    String orderDateTime) {
}
