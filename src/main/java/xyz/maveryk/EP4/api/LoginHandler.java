package xyz.maveryk.EP4.api;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.maveryk.EP4.entity.User;
import xyz.maveryk.EP4.response.LoginResponse;
import xyz.maveryk.EP4.service.UserService;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/login")
public class LoginHandler {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<LoginResponse> login(String username, String password) {
        ResponseEntity<LoginResponse> response;

        try {
            User user = userService.findByUserName(username);

            if (user != null && user.getPassword().equals(password)) {
                LoginResponse loginResponse = new LoginResponse("Inicio de sesión exitoso");
                response = ResponseEntity.ok(loginResponse);
            }
            else {
                response = ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new LoginResponse("Credenciales inválidas"));
            }
        } catch (DataIntegrityViolationException ex) {
            response = ResponseEntity
                    .badRequest()
                    .body(new LoginResponse("Error al iniciar sesión"));
        }

        return response;
    }

}
