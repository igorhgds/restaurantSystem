package igor.henrique.usecases.order;

import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import igor.henrique.entities.Order;
import igor.henrique.mappers.order_item.OrderItemStructMapper;
import igor.henrique.repositories.OrderItemJpaRepository;
import igor.henrique.repositories.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListOrderItemByOrderIdUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderItemStructMapper orderItemStructMapper;

    public List<OrderItemOutputDTO> listItems(Long orderId) {
        Order order = orderJpaRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com o ID: " + orderId));

        return orderItemStructMapper.toOrderItemOutputDTOList(order.getItems());
    }
}
