package igor.henrique.rest.specs;

import igor.henrique.dtos.order.input.CreateOrderInputDTO;
import igor.henrique.dtos.order.output.OrderDetailsOutputDTO;
import igor.henrique.dtos.order.output.OrderOutputDTO;
import igor.henrique.dtos.order.output.OrderTotalOutputDTO;
import igor.henrique.dtos.order_item.input.CreateOrderItemInputDTO;
import igor.henrique.dtos.order_item.output.OrderItemOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Tag(name = "4. Order")
public interface OrderControllerSpecs {

    @Operation(summary = "Cria um novo pedido",
            description = "Inicia um novo pedido para uma mesa específica.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
            })
    @PostMapping
    ResponseEntity<OrderOutputDTO> createOrder(@RequestBody CreateOrderInputDTO dto);

    @Operation(summary = "Fecha um pedido",
            description = "Finaliza um pedido, calculando o valor total.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pedido fechado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    @PutMapping("/{id}/close")
    ResponseEntity<Void> closeOrder(@PathVariable UUID id);

    @Operation(summary = "Lista pedidos com filtros",
            description = "Retorna uma lista de pedidos, com a opção de filtrar por mesa, garçom ou status.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso")
            })
    @GetMapping
    ResponseEntity<List<OrderOutputDTO>> listOrders(
            @Parameter(description = "Número da mesa para filtrar")
            @RequestParam(required = false) Integer tableNumber,
            @Parameter(description = "Nome do garçom para filtrar")
            @RequestParam(required = false) String waiterName,
            @Parameter(description = "Status do pedido para filtrar (ex: ABERTO, FECHADO)")
            @RequestParam(required = false) String orderStatus
    );

    @Operation(summary = "Adiciona um item ao pedido",
            description = "Adiciona um prato a um pedido existente.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Item adicionado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido ou prato não encontrados")
            })
    @PostMapping("/{orderId}/items")
    ResponseEntity<OrderItemOutputDTO> addItemToOrder(
            @PathVariable UUID orderId,
            @RequestBody @Valid CreateOrderItemInputDTO dto);

    @Operation(summary = "Remove um item do pedido",
            description = "Remove um item específico de um pedido.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Item removido com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido ou item não encontrados")
            })
    @DeleteMapping("/{orderId}/items/{orderItemId}")
    ResponseEntity<Void> removeItemFromOrder(
            @PathVariable UUID orderId,
            @PathVariable UUID orderItemId);

    @Operation(summary = "Lista itens de um pedido",
            description = "Retorna todos os itens (pratos) de um pedido específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Itens do pedido retornados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    @GetMapping("/{orderId}/items")
    ResponseEntity<List<OrderItemOutputDTO>> listItems(@PathVariable UUID orderId);

    @Operation(summary = "Detalhes de um pedido",
            description = "Retorna informações detalhadas de um pedido, incluindo itens e valores.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Detalhes do pedido retornados com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    @GetMapping("/{orderId}/details")
    ResponseEntity<OrderDetailsOutputDTO> getOrderDetails(@PathVariable UUID orderId);

    @Operation(summary = "Calcula o total de um pedido",
            description = "Calcula e retorna o valor total de um pedido, somando todos os itens.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Total do pedido calculado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
            })
    @GetMapping("/{orderId}/total")
    ResponseEntity<OrderTotalOutputDTO> calculateTotal(@PathVariable UUID orderId);
}