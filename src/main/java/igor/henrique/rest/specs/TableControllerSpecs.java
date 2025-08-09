package igor.henrique.rest.specs;

import igor.henrique.dtos.table.input.ChangeTableStatusInputDTO;
import igor.henrique.dtos.table.input.CreateTableInputDTO;
import igor.henrique.dtos.table.output.TableOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "3. Table")
public interface TableControllerSpecs {

    @Operation(summary = "Cria uma nova mesa",
            description = "Cria uma nova mesa com o número e o status inicial.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Mesa criada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos ou mesa já existente")
            })
    @PostMapping
    ResponseEntity<TableOutputDTO> create(@RequestBody @Valid CreateTableInputDTO dto);

    @Operation(summary = "Lista todas as mesas",
            description = "Retorna uma lista de todas as mesas cadastradas no sistema.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de mesas retornada com sucesso")
            })
    @GetMapping
    ResponseEntity<List<TableOutputDTO>> listTables();

    @Operation(summary = "Busca uma mesa por número",
            description = "Retorna os detalhes de uma mesa específica pelo seu número.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Mesa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Mesa não encontrada")
            })
    @GetMapping("/{tableNumber}")
    ResponseEntity<TableOutputDTO> getTable(@PathVariable Integer tableNumber);

    @Operation(summary = "Atualiza o status de uma mesa",
            description = "Altera o status de uma mesa (ex: de 'LIVRE' para 'OCUPADA').",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Status alterado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Mesa não encontrada")
            })
    @PatchMapping("/{tableNumber}/status")
    ResponseEntity<Void> chanceStatus(@PathVariable Integer tableNumber, @RequestBody ChangeTableStatusInputDTO dto);

    @Operation(summary = "Deleta uma mesa por número",
            description = "Remove uma mesa do sistema pelo seu número.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Mesa deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Mesa não encontrada")
            })
    @DeleteMapping("/{tableNumber}")
    ResponseEntity<Void> delete(@PathVariable Integer tableNumber);

    @Operation(summary = "Associa um garçom a uma mesa",
            description = "Atribui um garçom a uma mesa específica.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Garçom atribuído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Mesa ou garçom não encontrados")
            })
    @PatchMapping("/{tableNumber}/waiter/{waiterId}")
    ResponseEntity<Void> updateTableWaiter(@PathVariable Integer tableNumber, @PathVariable UUID waiterId);
}
