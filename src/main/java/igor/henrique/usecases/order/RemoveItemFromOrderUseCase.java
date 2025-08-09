package igor.henrique.usecases.order;

import igor.henrique.entities.Order;
import igor.henrique.entities.OrderItem;
import igor.henrique.enums.OrderStatus;
import igor.henrique.repositories.OrderItemJpaRepository;
import igor.henrique.repositories.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RemoveItemFromOrderUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;

    @Transactional
    public void removeItem(UUID orderId, UUID itemId) {
        Order order = orderJpaRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Não é possível remover itens de um pedido fechado.");
        }

        OrderItem itemToRemove = order.getItems().stream()
                .filter(item -> item.getOrderItemId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado no pedido"));

        order.getItems().remove(itemToRemove);

        orderItemJpaRepository.delete(itemToRemove);

        BigDecimal newTotal = order.getItems().stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalPrice(newTotal);

        orderJpaRepository.save(order);
    }
}