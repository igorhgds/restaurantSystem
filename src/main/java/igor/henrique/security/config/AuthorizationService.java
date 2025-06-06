package igor.henrique.security.config;

import igor.henrique.repositories.UserJpaRepository;
import igor.henrique.security.dto.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userJpaRepository.findByEmail(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserDetailsDTO(user.get());
    }
}
