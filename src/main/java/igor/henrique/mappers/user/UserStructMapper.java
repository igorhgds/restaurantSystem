package igor.henrique.mappers.user;

import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import igor.henrique.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserStructMapper {

    UserDetailedOutputDTO toUserDetailedOutputDTO(User entity);
    User toEntity(UserDetailedOutputDTO dto);
}
