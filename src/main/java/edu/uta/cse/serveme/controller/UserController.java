package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserToken;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/detail")
    public BaseResponse<User> detail(Authentication auth) {

        return new ResultResponse<>(userService.oneByIdWithInfo((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/getById")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<User> getById(@RequestParam Integer uid) {

        return new ResultResponse<>(userService.oneByIdWithInfo(uid));
    }

    @PostMapping(value = "/token")
    public BaseResponse<Integer> token(@RequestBody UserToken userToken, Authentication auth) {

        userToken.setUid((Integer) auth.getPrincipal());
        return new ResultResponse<>(userService.updateOrCreateToken(userToken));
    }
}
