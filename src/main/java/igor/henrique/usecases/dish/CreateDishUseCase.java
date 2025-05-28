package igor.henrique.usecases.dish;

import igor.henrique.dtos.dish.input.CreateDishInputDTO;
import igor.henrique.dtos.dish.output.DishOutputDTO;
import igor.henrique.entities.Dish;
import igor.henrique.mappers.dish.DishStructMapper;
import igor.henrique.repositories.DishJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateDishUseCase {

    private final DishJpaRepository dishJpaRepository;
    private final DishStructMapper dishStructMapper;

    public DishOutputDTO createDish(CreateDishInputDTO dto){

        dishJpaRepository.findByName(dto.name()).ifPresent(existing -> {
            throw new IllegalArgumentException("Já existe um prato com esse nome.");
        });

        Dish dish = dishStructMapper.toEntity(dto);
        dish.setName(dto.name());
        dish.setDescription(dto.description());
        dish.setPrice(dto.price());

        return dishStructMapper.toDishOutputDTO(dishJpaRepository.save(dish));

    }
}
