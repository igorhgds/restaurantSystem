package igor.henrique.mappers.dish;

import igor.henrique.dtos.dish.input.CreateDishInputDTO;
import igor.henrique.dtos.dish.output.DishOutputDTO;

import igor.henrique.entities.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishStructMapper {

    Dish toEntity(CreateDishInputDTO dto);
    DishOutputDTO toDishOutputDTO(Dish entity);
}
