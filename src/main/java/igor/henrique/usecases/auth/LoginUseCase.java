package igor.henrique.usecases.auth;

import igor.henrique.dtos.auth.input.LoginInputDTO;
import igor.henrique.dtos.auth.output.LoginOutputDTO;
import igor.henrique.security.dto.UserDetailsDTO;
import igor.henrique.security.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public LoginOutputDTO execute(LoginInputDTO input) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(input.getEmail().trim(), input.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var userDetails = (UserDetailsDTO) auth.getPrincipal();
        var token = this.jwtTokenService.generateToken(userDetails);

        return new LoginOutputDTO(token);
    }
}
