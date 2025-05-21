package igor.henrique.usecases.user;

import igor.henrique.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserJpaRepository userJpaRepository;

    public void execute(Long id) {
        userJpaRepository.deleteById(id);
    }
}
