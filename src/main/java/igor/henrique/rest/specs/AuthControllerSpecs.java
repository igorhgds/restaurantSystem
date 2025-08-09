package igor.henrique.rest.specs;

import igor.henrique.dtos.auth.input.LoginInputDTO;
import igor.henrique.dtos.auth.output.LoginOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "1. Auth")
public interface AuthControllerSpecs {

    @Operation(summary = "Autentica um usuário",
            description = "Realiza o login com email e senha para obter um token de autenticação.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
            })
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    LoginOutputDTO login(@RequestBody @Valid LoginInputDTO request);
}
