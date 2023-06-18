package mg.sinel.evento.controllers;

import custom.springutils.controller.CrudController;
import mg.sinel.evento.http.requests.AuthRequest;
import mg.sinel.evento.http.responses.AuthResponse;
import mg.sinel.evento.models.user.User;
import mg.sinel.evento.models.user.UserFilter;
import mg.sinel.evento.services.UserAuthService;
import mg.sinel.evento.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends CrudController<User, UserService, UserFilter> {
    private final UserAuthService userAuthService;

    public UserController(UserService service, UserAuthService userAuthService) {
        super(service);
        this.userAuthService = userAuthService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
        AuthResponse response = authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    private AuthResponse authenticate(AuthRequest request) throws Exception {
        try {
            return userAuthService.authenticate(request);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
