package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserRole;
import edu.uta.cse.serveme.entity.Vendor;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping(value = "/myself")
    public BaseResponse<User> myself(Authentication auth) {
        return new ResultResponse<>(userService.findUserById((Long) auth.getPrincipal()));
    }

    @PutMapping(value = "/update")
    public BaseResponse<User> update(@RequestBody User user, Authentication auth) {
        user.setId((Long) auth.getPrincipal());
        return new ResultResponse<>(userService.update(user));
    }

    @PutMapping(value = "/vendor")
    public BaseResponse<Vendor> vendor(@RequestBody Vendor vendor, Authentication auth) {
        User user = (User) auth.getDetails();
        if (!user.getRole().contains(UserRole.ROLE_VENDOR)) {
            user.getRole().add(UserRole.ROLE_VENDOR);
        }
        vendor.setUid(user.getId());
        user.setVendor(vendor);
        userService.update(user);
        return new ResultResponse<>(vendor);
    }
}
