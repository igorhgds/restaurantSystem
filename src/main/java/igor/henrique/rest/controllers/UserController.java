package igor.henrique.rest.controllers;

import igor.henrique.dtos.table.output.TableOutputDTO;
import igor.henrique.dtos.user.input.CreateUserInputDTO;
import igor.henrique.dtos.user.output.UserDetailedOutputDTO;
import igor.henrique.usecases.user.CreateUserUseCase;
import igor.henrique.usecases.user.DeleteUserUseCase;
import igor.henrique.usecases.user.FindUserByIdUseCase;
import igor.henrique.usecases.user.ListUsersUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "UserController", description = "Operações relacionadas aos usuários do restaurante")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final ListUsersUseCase listUsersUseCase;

    @PostMapping
    public ResponseEntity<UserDetailedOutputDTO> create(@RequestBody @Valid CreateUserInputDTO dto) {
        UserDetailedOutputDTO response = createUserUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailedOutputDTO> findByUserId(@PathVariable Long id) {
        var user = findUserByIdUseCase.execute(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDetailedOutputDTO>> listUsers(){
        List<UserDetailedOutputDTO> users = listUsersUseCase.listUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDetailedOutputDTO> delete(@PathVariable Long id) {
        var user = findUserByIdUseCase.execute(id);
        deleteUserUseCase.delete(id);
        return ResponseEntity.ok(user);
    }
}
