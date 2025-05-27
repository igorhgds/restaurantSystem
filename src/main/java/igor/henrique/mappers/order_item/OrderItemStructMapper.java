package igor.henrique.mappers.order_item;

import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.entities.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemStructMapper {

    OrderItem toEntity(CreateOrderItemInputDTO dto);
    OrderItemOutputDTO toOrderItemOutputDTO(OrderItem input);
}
