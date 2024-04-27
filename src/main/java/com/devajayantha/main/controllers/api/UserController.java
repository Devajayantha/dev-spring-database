package com.devajayantha.main.controllers.api;

import com.devajayantha.main.config.ResponseData;
import com.devajayantha.main.models.dtos.UserDto;
import com.devajayantha.main.models.entities.User;
import com.devajayantha.main.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    protected UserService userService;

    @PostMapping
    public ResponseData register(@Valid @RequestBody UserDto userDto, Errors errors) {
        ResponseData errorMessages = ResponseData.getResponseData(errors);
        if (errorMessages != null) return errorMessages;

        User user =  userService.createUser(userDto);

        return new ResponseData("User created successfully", HttpStatus.CREATED, user);
    }
}
