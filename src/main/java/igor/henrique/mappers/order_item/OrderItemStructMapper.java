package igor.henrique.mappers.order_item;

import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.entities.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemStructMapper {

    OrderItem toEntity(CreateOrderItemInputDTO dto);

    @Mapping(source = "order.orderId", target = "orderId")
    @Mapping(source = "dish.dishId", target = "dishId")
    @Mapping(source = "dish.name", target = "dishName")
    OrderItemOutputDTO toOrderItemOutputDTO(OrderItem input);
}

