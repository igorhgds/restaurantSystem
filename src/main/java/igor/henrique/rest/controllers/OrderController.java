package igor.henrique.rest.controllers;

import igor.henrique.dtos.order.input.CreateOrderInputDTO;
import igor.henrique.dtos.order.input.ListOrdersFilterInputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.usecases.order.CloseOrderUseCase;
import igor.henrique.usecases.order.CreateOrderUseCase;
import igor.henrique.usecases.order.ListOrdersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final CloseOrderUseCase closeOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;

    @PostMapping
    public ResponseEntity<OrderOutputDTO> createOrder(@RequestBody CreateOrderInputDTO dto) {
        OrderOutputDTO response = createOrderUseCase.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<Void> closeOrder(@PathVariable Long id) {
        closeOrderUseCase.closeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderOutputDTO>> listOrders(
            @RequestParam(required = false) Integer tableNumber,
            @RequestParam(required = false) String waiterName,
            @RequestParam(required = false) String orderStatus
    ) {
        ListOrdersFilterInputDTO filter = new ListOrdersFilterInputDTO(tableNumber, waiterName, orderStatus);
        List<OrderOutputDTO> result = listOrdersUseCase.listOrders(filter);
        return ResponseEntity.ok(result);
    }


}
