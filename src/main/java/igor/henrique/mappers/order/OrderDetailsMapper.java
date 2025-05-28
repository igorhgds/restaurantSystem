package igor.henrique.mappers.order;

import igor.henrique.dtos.order.output.OrderDetailsOutputDTO;
import igor.henrique.dtos.order_item.output.OrderItemDTO;
import igor.henrique.entities.Order;
import igor.henrique.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailsMapper {

    @Mapping(source = "table.tableNumber", target = "tableNumber")
    @Mapping(source = "waiter.name", target = "waiterName")
    @Mapping(source = "status", target = "orderStatus")
    @Mapping(target = "orderDateTime", expression = "java(order.getCreatedAt().format(java.time.format.DateTimeFormatter.ofPattern(\"dd/MM/yyyy HH:mm\")))")
    @Mapping(target = "totalAmount", expression = "java(order.calculateTotal())")
    @Mapping(source = "items", target = "items")
    OrderDetailsOutputDTO toOrderDetailsOutputDTO(Order order);

    @Mapping(source = "dish.name", target = "dishName")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "unitPrice", target = "unitPrice")
    @Mapping(target = "totalPrice", expression = "java(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())))")
    OrderItemDTO toOrderItemDTO(OrderItem item);
}

