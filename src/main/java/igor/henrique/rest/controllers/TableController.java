package igor.henrique.rest.controllers;

import igor.henrique.dtos.table.input.CreateTableInputDTO;
import igor.henrique.dtos.table.output.TableOutputDTO;
import igor.henrique.usecases.table.CreateTableUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tables")
public class TableController {

    private final CreateTableUseCase createTableUseCase;

    @PostMapping
    public ResponseEntity<TableOutputDTO> create(@RequestBody @Valid CreateTableInputDTO dto) {
        TableOutputDTO response = createTableUseCase.createTable(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
