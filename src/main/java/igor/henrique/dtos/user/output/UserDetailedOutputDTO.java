package igor.henrique.dtos.user.output;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UserDetailedOutputDTO (
        Long id,

        @Schema(example = "Igor")
        String name,

        @Schema(example = "igor@example.com")
        String email
){
}
