package igor.henrique.security.filter;

import igor.henrique.repositories.UserJpaRepository;
import igor.henrique.security.dto.UserDetailsDTO;
import igor.henrique.security.services.JwtTokenService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserJpaRepository userJpaRepository;
    //private final MessageService messageService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final var token = this.extractToken(request);

        if (Objects.isNull(token)){
            filterChain.doFilter(request, response);
            return;
        }

        this.authenticate(this.jwtTokenService.getUserId(token), request);
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request){
        final var token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer "))
            return null;

        return token.substring(7);
    }

    // O parâmetro 'userIdString' ainda é uma String, vindo do JWT
    private void authenticate(String userIdString, HttpServletRequest request ){
        // *** MUDANÇA AQUI: Converter a String do JWT para Long ***
        Long userIdAsLong;
        try {
            userIdAsLong = Long.parseLong(userIdString);
        } catch (NumberFormatException e) {
            // Se o subject do JWT não for um número válido, isso é um erro de autenticação.
            // Permita que a exceção seja propagada para o tratamento padrão do Spring Security (401).
            throw new RuntimeException("Invalid user ID in token: " + userIdString, e);
        }

        final var user = this.userJpaRepository.findByUserId(userIdAsLong)
                .orElseThrow(EntityNotFoundException::new);

        final var userDetails = new UserDetailsDTO(user);

        final var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
