package igor.henrique.dtos.user.output;

import igor.henrique.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserDetailedOutputDTO (
        Long id,

        @Schema(example = "Igor")
        String name,

        @Schema(example = "igor@example.com")
        String email,

        @Schema(example = "WAITER")
        UserRole role
){
}
