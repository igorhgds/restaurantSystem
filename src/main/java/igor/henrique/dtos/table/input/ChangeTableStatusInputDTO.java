package igor.henrique.dtos.table.input;

import igor.henrique.enums.TableStatus;


public record ChangeTableStatusInputDTO(TableStatus newStatus) {
}
