package igor.henrique.usecases.order_item;

import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.entities.OrderItem;
import igor.henrique.mappers.order_item.OrderItemStructMapper;
import igor.henrique.repositories.OrderItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderItemUseCase {

    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderItemStructMapper orderItemStructMapper;

    public OrderItemOutputDTO createOrderItem(CreateOrderItemInputDTO dto) {
        orderItemJpaRepository.findByOrderItemId(dto.dishId()).ifPresent(existing -> {
            throw new IllegalArgumentException("Já existe um item de pedido com esse ID.");
        });

        OrderItem orderItem = orderItemStructMapper.toEntity(dto);
        orderItem.setDish(orderItem.getDish());
        orderItem.setOrder(orderItem.getOrder());
        orderItem.setQuantity(dto.quantity());
        orderItem.setUnitPrice(dto.unitPrice());

        return orderItemStructMapper.toOrderItemOutputDTO(orderItemJpaRepository.save(orderItem));

    }
}
