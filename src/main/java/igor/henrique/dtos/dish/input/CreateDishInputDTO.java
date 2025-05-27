package igor.henrique.dtos.dish.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateDishInputDTO(
        @NotBlank
        String name,

        @NotNull
        String description,

        @NotNull
        BigDecimal price
) {
}
