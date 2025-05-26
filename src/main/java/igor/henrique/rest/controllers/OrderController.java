package igor.henrique.rest.controllers;

import igor.henrique.dtos.order.input.CreateOrderInputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.usecases.order.CreateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping
    public ResponseEntity<OrderOutputDTO> createOrder(@RequestBody CreateOrderInputDTO dto) {
        OrderOutputDTO response = createOrderUseCase.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
