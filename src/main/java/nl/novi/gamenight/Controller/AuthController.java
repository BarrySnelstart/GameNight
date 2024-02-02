package nl.novi.gamenight.Controller;

import nl.novi.gamenight.Dto.Auth.AuthDto;
import nl.novi.gamenight.Model.Logging;
import nl.novi.gamenight.Repository.LoggingRepository;
import nl.novi.gamenight.security.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
public class AuthController {
    private final LoggingRepository loggingRepository;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(LoggingRepository loggingRepository, AuthenticationManager man, JwtService service) {
        this.loggingRepository = loggingRepository;
        this.authManager = man;
        this.jwtService = service;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(authDto.username, authDto.password);
        Logging log = new Logging();
        try {
            Authentication auth = authManager.authenticate(up);

            UserDetails ud = (UserDetails) auth.getPrincipal();

            String token = jwtService.generateToken(ud);
            log.setUsername(authDto.username);
            log.setLogDate(LocalDateTime.now());
            log.setPassword("Correct password");
            log.setMessage("Successful signing");
            loggingRepository.save(log);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body("Token generated");


        }
        catch (AuthenticationException ex) {

            log.setPassword(authDto.password);
            log.setUsername(authDto.username);
            log.setMessage(ex.getMessage());


            log.setLogDate(LocalDateTime.now());
            loggingRepository.save(log);

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
