package igor.henrique.usecases.order;

import igor.henrique.entities.Order;
import igor.henrique.enums.OrderStatus;
import igor.henrique.enums.TableStatus;
import igor.henrique.repositories.OrderJpaRepository;
import igor.henrique.usecases.table.ChangeTableStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloseOrderUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final ChangeTableStatusUseCase changeTableStatusUseCase;

    public void closeOrder(Long orderId) {
        Order order = orderJpaRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        if (order.getStatus() == OrderStatus.CLOSED) {
            throw new IllegalArgumentException("Pedido já está fechado");
        }

        order.setStatus(OrderStatus.CLOSED);
        orderJpaRepository.save(order);

         Integer tableNumber = order.getTable().getTableNumber();
        changeTableStatusUseCase.changeStatus(tableNumber, TableStatus.AVAILABLE);
    }
}
