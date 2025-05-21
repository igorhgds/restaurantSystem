package igor.henrique.rest.controllers;

import igor.henrique.dtos.user.input.CreateUserInputDTO;
import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import igor.henrique.usecases.user.CreateUserUseCase;
import igor.henrique.usecases.user.DeleteUserUseCase;
import igor.henrique.usecases.user.FindUserByIdUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public ResponseEntity<UserDetailedOutputDTO> create(@RequestBody @Valid CreateUserInputDTO dto) {
        UserDetailedOutputDTO response = createUserUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailedOutputDTO> findById(@PathVariable Long id) {
        var user = findUserByIdUseCase.execute(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDetailedOutputDTO> delete(@PathVariable Long id) {
        var user = findUserByIdUseCase.execute(id);
        deleteUserUseCase.delete(id);
        return ResponseEntity.ok(user);
    }
}
