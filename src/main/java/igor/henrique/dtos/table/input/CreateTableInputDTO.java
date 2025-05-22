package igor.henrique.dtos.table.input;

import jakarta.validation.constraints.NotNull;

public record CreateTableInputDTO(

        @NotNull
        int tableNumber,

        @NotNull
        Long waiterId
) {
}
