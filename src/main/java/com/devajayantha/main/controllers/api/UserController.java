package com.devajayantha.main.controllers.api;

import com.devajayantha.main.config.ResponseData;
import com.devajayantha.main.models.dtos.LoginDao;
import com.devajayantha.main.models.dtos.UserDto;
import com.devajayantha.main.models.entities.User;
import com.devajayantha.main.models.responses.JwtResponse;
import com.devajayantha.main.services.UserService;
import com.devajayantha.main.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    protected UserService userService;

    @Autowired
    protected AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping
    public ResponseData register(@Valid @RequestBody UserDto userDto, Errors errors) {
        ResponseData errorMessages = ResponseData.getResponseData(errors);
        if (errorMessages != null) return errorMessages;

        User user = userService.createUser(userDto);

        return new ResponseData("User created successfully", HttpStatus.CREATED, user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDao loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getRole().toString()));
    }
}
