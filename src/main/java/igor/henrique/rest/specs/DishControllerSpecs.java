package igor.henrique.rest.specs;

import igor.henrique.dtos.dish.input.CreateDishInputDTO;
import igor.henrique.dtos.dish.output.DishOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "6. Dish")
public interface DishControllerSpecs {

    @Operation(summary = "Cria um novo prato",
            description = "Adiciona um novo prato ao cardápio com informações de nome, preço e categoria.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Prato criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
            })
    @PostMapping
    ResponseEntity<DishOutputDTO> createDish(@RequestBody @Valid CreateDishInputDTO dto);
}
