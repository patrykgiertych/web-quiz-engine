package engine.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("Successful registration!");
    }
}
