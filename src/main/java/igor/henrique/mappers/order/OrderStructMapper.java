package igor.henrique.mappers.order;

import igor.henrique.dtos.order.input.CreateOrderInputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderStructMapper {

    @Mapping(target = "table", ignore = true)
    @Mapping(target = "waiter", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    Order toEntity(CreateOrderInputDTO dto);

    @Mapping(target = "tableNumber", source = "table.tableNumber")
    @Mapping(target = "waiterName", source = "waiter.name")
    @Mapping(target = "orderStatus", source = "status")
    @Mapping(target = "orderDateTime", expression = "java(entity.getCreatedAt().format(java.time.format.DateTimeFormatter.ofPattern(\"dd/MM/yyyy HH:mm\")))")
    @Mapping(target = "totalPrice", expression = "java(0.0)")
    OrderOutputDTO toOrderOutputDTO(Order entity);
}
