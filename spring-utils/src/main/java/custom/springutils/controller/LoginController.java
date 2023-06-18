package custom.springutils.controller;

import static custom.springutils.util.ControllerUtil.returnSuccess;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import custom.springutils.LoginEntity;
import custom.springutils.service.ServiceLogin;
import jakarta.servlet.http.HttpServletRequest;

public abstract class LoginController <E extends LoginEntity, S extends ServiceLogin<E>> {

    protected S service;

    public LoginController(S service) {
        this.service = service;
    }

    public abstract String getRequestHeaderKey ();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody E entity) throws Exception {
        return returnSuccess(service.login(entity), HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws Exception {
        return returnSuccess(service.logout(request.getHeader(getRequestHeaderKey())), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody E entity) throws Exception {
        return returnSuccess(service.register(entity), HttpStatus.OK);
    }

    @PostMapping("/isconnected")
    public ResponseEntity<?> isConnected(HttpServletRequest request) throws Exception {
        return returnSuccess(service.isConnected(request.getHeader(getRequestHeaderKey())), HttpStatus.OK);
    }

}
