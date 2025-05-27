package igor.henrique.usecases.order;

import igor.henrique.dtos.order.input.ListOrdersFilterInputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
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

    public List<OrderOutputDTO> listOrders(ListOrdersFilterInputDTO filter){
        String orderStatus = filter.orderStatus();
        if (orderStatus != null && !orderStatus.isBlank()) {
            orderStatus = orderStatus.toUpperCase();
        }

        return orderJpaRepository.findFiltered(
                filter.tableNumber(),
                filter.waiterName(),
                orderStatus)
                .stream()
                .map(orderStructMapper::toOrderOutputDTO)
                .toList();

    }

}
