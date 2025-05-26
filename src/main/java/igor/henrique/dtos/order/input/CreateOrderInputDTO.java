package igor.henrique.dtos.order.input;

import jakarta.validation.constraints.NotNull;

public record CreateOrderInputDTO(
        @NotNull(message = "O número da mesa é obrigatório.")
        Integer tableNumber,

        @NotNull(message = "O ID do garçom é obrigatório.")
        Long waiterId
) {
}
