package igor.henrique.usecases.user;

import igor.henrique.dtos.user.input.CreateUserInputDTO;
import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import igor.henrique.entities.User;
import igor.henrique.mappers.user.UserStructMapper;
import igor.henrique.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserJpaRepository userJpaRepository;
    private final UserStructMapper userStructMapper;
    private final BCryptPasswordEncoder encoder;

    public UserDetailedOutputDTO execute(CreateUserInputDTO dto) {
        if (userJpaRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        User user = userStructMapper.toEntity(dto);
        user.setPassword(encoder.encode(dto.password()));

        return userStructMapper.toUserDetailedOutputDTO(userJpaRepository.save(user));
    }

}

