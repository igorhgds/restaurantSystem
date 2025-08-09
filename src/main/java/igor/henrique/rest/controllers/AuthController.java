package igor.henrique.rest.controllers;

import igor.henrique.dtos.auth.input.LoginInputDTO;
import igor.henrique.dtos.auth.output.LoginOutputDTO;
import igor.henrique.rest.specs.AuthControllerSpecs;
import igor.henrique.usecases.auth.LoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController implements AuthControllerSpecs {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public LoginOutputDTO login(@RequestBody @Valid LoginInputDTO request) {
        return loginUseCase.execute(request);
    }
}
