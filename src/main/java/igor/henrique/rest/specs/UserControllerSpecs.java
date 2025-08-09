package igor.henrique.rest.specs;

import igor.henrique.dtos.user.input.CreateUserInputDTO;
import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "UserController", description = "Operações relacionadas aos usuários do restaurante")
public interface UserControllerSpecs {

    @Operation(summary = "Cria um novo usuário",
            description = "Cria um novo usuário com os dados fornecidos no corpo da requisição.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
            })
    @PostMapping
    ResponseEntity<UserDetailedOutputDTO> create(@RequestBody @Valid CreateUserInputDTO dto);

    @Operation(summary = "Busca um usuário por ID",
            description = "Retorna os detalhes de um usuário específico pelo seu ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            })
    @GetMapping("/{id}")
    ResponseEntity<UserDetailedOutputDTO> findByUserId(@PathVariable UUID id);

    @Operation(summary = "Lista todos os usuários",
            description = "Retorna uma lista completa de todos os usuários cadastrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
            })
    @GetMapping
    ResponseEntity<List<UserDetailedOutputDTO>> listUsers();

    @Operation(summary = "Deleta um usuário por ID",
            description = "Deleta um usuário específico pelo seu ID e retorna os detalhes do usuário deletado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            })
    @DeleteMapping("/{id}")
    ResponseEntity<UserDetailedOutputDTO> delete(@PathVariable UUID id);
}