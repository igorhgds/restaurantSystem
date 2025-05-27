package igor.henrique.usecases.user;

import igor.henrique.repositories.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCase {

    private final UserJpaRepository userJpaRepository;

    public void delete(Long id) {
        var user = userJpaRepository.findByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
        userJpaRepository.delete(user);
    }
}
