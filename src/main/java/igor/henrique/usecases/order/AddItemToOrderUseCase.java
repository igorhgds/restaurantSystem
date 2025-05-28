package igor.henrique.usecases.order;

import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.entities.Order;
import igor.henrique.enums.OrderStatus;
import igor.henrique.repositories.OrderJpaRepository;
import igor.henrique.usecases.order_item.CreateOrderItemUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AddItemToOrderUseCase {

    private final OrderJpaRepository orderRepository;
    private final CreateOrderItemUseCase createOrderItemUseCase;

    public OrderItemOutputDTO addItem(CreateOrderItemInputDTO dto) {
        Order order = orderRepository.findById(dto.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (order.getStatus() == OrderStatus.CLOSED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Não é possível adicionar itens a um pedido fechado ou cancelado");
        }

        OrderItemOutputDTO itemDTO = createOrderItemUseCase.createOrderItem(dto);

        BigDecimal itemTotal = BigDecimal.valueOf(itemDTO.unitPrice())
                .multiply(BigDecimal.valueOf(itemDTO.quantity()));

        if (order.getTotalPrice() == null) {
            order.setTotalPrice(BigDecimal.ZERO);
        }

        order.setTotalPrice(order.getTotalPrice().add(itemTotal));
        orderRepository.save(order);

        return itemDTO;
    }
}
