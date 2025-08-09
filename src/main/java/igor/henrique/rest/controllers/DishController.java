package igor.henrique.rest.controllers;

import igor.henrique.dtos.dish.input.CreateDishInputDTO;
import igor.henrique.dtos.dish.output.DishOutputDTO;
import igor.henrique.rest.specs.DishControllerSpecs;
import igor.henrique.usecases.dish.CreateDishUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController implements DishControllerSpecs {

    private final CreateDishUseCase createDishUseCase;

     @PostMapping
     public ResponseEntity<DishOutputDTO> createDish(@RequestBody CreateDishInputDTO dto) {
         DishOutputDTO response = createDishUseCase.createDish(dto);
         return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }
}
