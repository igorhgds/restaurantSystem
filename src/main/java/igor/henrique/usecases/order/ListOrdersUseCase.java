package igor.henrique.usecases.order;

import igor.henrique.dtos.order.input.ListOrdersFilterInputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.enums.OrderStatus;
import igor.henrique.mappers.order.OrderStructMapper;
import igor.henrique.repositories.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListOrdersUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderStructMapper orderStructMapper;

    public List<OrderOutputDTO> listOrders(ListOrdersFilterInputDTO filter) {
        OrderStatus status = null;
        if (filter.orderStatus() != null && !filter.orderStatus().isBlank()) {
            try {
                status = OrderStatus.valueOf(filter.orderStatus().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Status inválido: " + filter.orderStatus());
            }
        }

        String waiterName = filter.waiterName();
        if (waiterName != null && !waiterName.isBlank()) {
            waiterName = "%" + waiterName.toLowerCase() + "%";
        }

        return orderJpaRepository.findFiltered(
                        filter.tableNumber(),
                        waiterName,
                        status
                ).stream()
                .map(orderStructMapper::toOrderOutputDTO)
                .toList();
    }
}
