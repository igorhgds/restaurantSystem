package igor.henrique.usecases.user;

import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import igor.henrique.mappers.user.UserStructMapper;
import igor.henrique.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListUsersUseCase {

    private final UserJpaRepository userJpaRepository;
    private final UserStructMapper userStructMapper;

    public List<UserDetailedOutputDTO> listUsers(){
        return userJpaRepository.findAll()
                .stream()
                .map(userStructMapper::toUserDetailedOutputDTO)
                .toList();
    }

}
