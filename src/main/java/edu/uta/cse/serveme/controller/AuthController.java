package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.service.UserService;
import edu.uta.cse.serveme.validator.Login;
import edu.uta.cse.serveme.validator.Register;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {
     private final UserService userService;

    @PostMapping(value = "/login")
    public BaseResponse<String> login(@RequestBody @Validated(value = Login.class) User user) {

        return new ResultResponse<>(userService.login(user));
    }

    @PostMapping(value = "/register")
    public BaseResponse<String> signUp(@RequestBody @Validated(value = Register.class) User user) {

        return new ResultResponse<>(userService.register(user));
    }
}
