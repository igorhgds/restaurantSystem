package igor.henrique.rest.controllers;

import igor.henrique.dtos.table.input.ChangeTableStatusInputDTO;
import igor.henrique.dtos.table.input.CreateTableInputDTO;
import igor.henrique.dtos.table.output.TableOutputDTO;
import igor.henrique.usecases.table.ChangeTableStatusUseCase;
import igor.henrique.usecases.table.CreateTableUseCase;
import igor.henrique.usecases.table.DeleteTableUseCase;
import igor.henrique.usecases.table.FindTableByNumberTableUseCase;
import igor.henrique.usecases.table.ListTablesUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableController {

    private final CreateTableUseCase createTableUseCase;
    private final ListTablesUseCase listTablesUseCase;
    private final FindTableByNumberTableUseCase findTableByNumberTableUseCase;
    private final ChangeTableStatusUseCase changeTableStatusUseCase;
    private final DeleteTableUseCase deleteTableUseCase;

    @PostMapping
    public ResponseEntity<TableOutputDTO> create(@RequestBody @Valid CreateTableInputDTO dto) {
        TableOutputDTO response = createTableUseCase.createTable(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TableOutputDTO>> listTables() {
        List<TableOutputDTO> tables = listTablesUseCase.listTables();
        return ResponseEntity.ok(tables);
    }

    @GetMapping("/{tableNumber}")
    public ResponseEntity<TableOutputDTO> getTable(@PathVariable Integer tableNumber) {
        TableOutputDTO table = findTableByNumberTableUseCase.getTable(tableNumber);
        return ResponseEntity.ok(table);
    }

    @PatchMapping("/{tableNumber}/status")
    public ResponseEntity<Void> chanceStatus(@PathVariable Integer tableNumber, @RequestBody ChangeTableStatusInputDTO dto){
        changeTableStatusUseCase.changeStatus(tableNumber, dto.newStatus());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{tableNumber}")
    public ResponseEntity<Void> delete(@PathVariable Integer tableNumber) {
        deleteTableUseCase.delete(tableNumber);
        return ResponseEntity.noContent().build();
    }
}
