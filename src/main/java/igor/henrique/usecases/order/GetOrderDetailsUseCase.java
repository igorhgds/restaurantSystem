package igor.henrique.usecases.order;

import igor.henrique.dtos.order.output.OrderDetailsOutputDTO;
import igor.henrique.mappers.order.OrderDetailsMapper;
import igor.henrique.repositories.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetOrderDetailsUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDetailsMapper orderDetailsMapper;

    public OrderDetailsOutputDTO execute(Long orderId) {
        return orderJpaRepository.findById(orderId)
                .map(orderDetailsMapper::toOrderDetailsOutputDTO)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
    }
}
