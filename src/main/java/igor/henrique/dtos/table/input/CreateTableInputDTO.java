package igor.henrique.dtos.table.input;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTableInputDTO(

        @NotNull
        int tableNumber,

        @NotNull
        UUID waiterId
) {
}
