package igor.henrique.dtos.table.output;

import igor.henrique.enums.TableStatus;

import java.util.UUID;

public record TableOutputDTO(
        UUID tableId,
        Integer tableNumber,
        TableStatus tableStatus,
        String waiterName
) {
}
