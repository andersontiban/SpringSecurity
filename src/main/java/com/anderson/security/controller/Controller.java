package com.anderson.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class Controller {

    private final AuthenticateService service;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(service.authenticate(request));
    }

}
