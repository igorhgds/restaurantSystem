package igor.henrique.mappers.user;

import igor.henrique.dtos.user.input.CreateUserInputDTO;
import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import igor.henrique.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStructMapper {
    User toEntity(CreateUserInputDTO dto);
    UserDetailedOutputDTO toUserDetailedOutputDTO(User entity);
}

