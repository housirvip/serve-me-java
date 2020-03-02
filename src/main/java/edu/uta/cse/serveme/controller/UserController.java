package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.Address;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.UserRole;
import edu.uta.cse.serveme.entity.Vendor;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/vendor")
    public BaseResponse<Vendor> getVendor(Authentication auth) {
        return new ResultResponse<>(userService.findVendorByUser((User) auth.getDetails()));
    }

    @PutMapping(value = "/vendor")
    public BaseResponse<Vendor> putVendor(@RequestBody Vendor vendor, Authentication auth) {
        User user = (User) auth.getDetails();
        if (!user.getRole().contains(UserRole.ROLE_VENDOR)) {
            user.getRole().add(UserRole.ROLE_VENDOR);
            userService.update(user);
        }
        vendor.setUser(user);
        return new ResultResponse<>(userService.update(vendor));
    }

    @GetMapping(value = "/address")
    public BaseResponse<List<Address>> getAddress(Authentication auth) {
        return new ResultResponse<>(userService.getAddress((User) auth.getDetails()));
    }

    @PutMapping(value = "/address")
    public BaseResponse<Address> putAddress(@RequestBody Address address, Authentication auth) {
        address.setUser((User) auth.getDetails());
        return new ResultResponse<>(userService.update(address));
    }
}
