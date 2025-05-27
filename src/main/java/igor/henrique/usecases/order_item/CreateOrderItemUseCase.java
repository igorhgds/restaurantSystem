package igor.henrique.usecases.order_item;

import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.entities.Dish;
import igor.henrique.entities.Order;
import igor.henrique.entities.OrderItem;
import igor.henrique.mappers.order_item.OrderItemStructMapper;
import igor.henrique.repositories.DishJpaRepository;
import igor.henrique.repositories.OrderItemJpaRepository;
import igor.henrique.repositories.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateOrderItemUseCase {

    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderItemStructMapper orderItemStructMapper;
    private final OrderJpaRepository orderJpaRepository;
    private final DishJpaRepository dishJpaRepository;

    public OrderItemOutputDTO createOrderItem(CreateOrderItemInputDTO dto) {
        Order order = orderJpaRepository.findById(dto.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com o ID: " + dto.orderId()));

        Dish dish = dishJpaRepository.findById(dto.dishId())
                .orElseThrow(() -> new IllegalArgumentException("Prato não encontrado com o ID: " + dto.dishId()));

        OrderItem orderItem = orderItemStructMapper.toEntity(dto);
        orderItem.setOrder(order);
        orderItem.setDish(dish);
        orderItem.setQuantity(dto.quantity());
        orderItem.setUnitPrice(dto.unitPrice());

        if (orderItem.getUnitPrice() == null) {
            orderItem.setUnitPrice(dish.getPrice());
        }

        OrderItem savedItem = orderItemJpaRepository.save(orderItem);

        BigDecimal itemTotal = savedItem.getUnitPrice()
                .multiply(BigDecimal.valueOf(savedItem.getQuantity()));

        if (order.getTotalPrice() == null) {
            order.setTotalPrice(BigDecimal.ZERO);
        }

        order.setTotalPrice(order.getTotalPrice().add(itemTotal));
        orderJpaRepository.save(order);

        return orderItemStructMapper.toOrderItemOutputDTO(savedItem);
    }
}

