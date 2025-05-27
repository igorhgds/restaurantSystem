package igor.henrique.dtos.user.input;

import igor.henrique.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record CreateUserInputDTO(

        @NotBlank
        @Schema(example = "Igor", requiredMode = REQUIRED)
        String name,

        @NotBlank
        @Email
        @Schema(example = "igor@example.com", requiredMode = REQUIRED)
        String email,

        @NotBlank
        @Schema(example = "P4ssw0rd!", requiredMode = REQUIRED)
        String password,

        @NotNull
        @Schema(description = "Perfil do usuário (ADMIN ou WAITER)", example = "WAITER", requiredMode = REQUIRED)
        UserRole role
) {

        @Override
        public String toString() {
                return "CreateUserInputDTO[name= %s, email= %s, role= %s]"
                        .formatted(name, email, role);
        }
}
