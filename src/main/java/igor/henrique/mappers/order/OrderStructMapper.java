package igor.henrique.mappers.order;

import igor.henrique.dtos.order.input.CreateOrderInputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStructMapper {
    Order toEntity(CreateOrderInputDTO dto);
    OrderOutputDTO toOrderOutputDTO(Order entity);
}
