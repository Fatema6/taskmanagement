package banquemisr.challenge05.taskmanagement.controllers;

import banquemisr.challenge05.taskmanagement.models.User;
import banquemisr.challenge05.taskmanagement.repositories.UserRepository;
import banquemisr.challenge05.taskmanagement.util.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername())
                .filter(foundUser -> passwordEncoder.matches(user.getPassword(), foundUser.getPassword()))
                .map(foundUser -> {
                    String token = jwtUtils.generateToken(foundUser.getUsername(), foundUser.getRole().name());
                    return ResponseEntity.ok(token);
                })
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }
}