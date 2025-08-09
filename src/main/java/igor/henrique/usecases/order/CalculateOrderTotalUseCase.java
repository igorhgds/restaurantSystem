package igor.henrique.usecases.order;

import igor.henrique.dtos.order.output.OrderTotalOutputDTO;
import igor.henrique.entities.Order;
import igor.henrique.repositories.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalculateOrderTotalUseCase {

    private final OrderJpaRepository orderJpaRepository;

    public OrderTotalOutputDTO calculateTotal(UUID orderId) {
        Order order = orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com id: " + orderId));

        BigDecimal total = order.getItems().stream()
                .map(item -> item.getDish().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderTotalOutputDTO(order.getOrderId(), total);
    }
}
