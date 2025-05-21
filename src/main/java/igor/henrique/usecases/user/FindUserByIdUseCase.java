package igor.henrique.usecases.user;

import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import igor.henrique.entities.User;
import igor.henrique.mappers.user.UserStructMapper;
import igor.henrique.repositories.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByIdUseCase {

    private final UserJpaRepository userJpaRepository;
    private final UserStructMapper userStructMapper;

    public UserDetailedOutputDTO execute(Long id) {
        var user = this.userJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));

        return this.userStructMapper.toUserDetailedOutputDTO(user);
    }

}
