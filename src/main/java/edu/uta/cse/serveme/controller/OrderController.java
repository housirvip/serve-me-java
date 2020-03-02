package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.Bid;
import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.entity.User;
import edu.uta.cse.serveme.entity.Vendor;
import edu.uta.cse.serveme.service.OrderService;
import edu.uta.cse.serveme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public BaseResponse<Order> get(@PathVariable Long id, Authentication auth) {
        Order order = new Order();
        order.setId(id);
        order.setUser((User) auth.getDetails());
        return new ResultResponse<>(orderService.findOrder(order));
    }

    @PostMapping(value = "")
    public BaseResponse<Order> create(@RequestBody Order order, Authentication auth) {
        order.setUser((User) auth.getDetails());
        return new ResultResponse<>(orderService.create(order));
    }

    @PutMapping(value = "")
    public BaseResponse<Order> update(@RequestBody Order order, Authentication auth) {
        order.setUser((User) auth.getDetails());
        return new ResultResponse<>(orderService.update(order));
    }

    @PutMapping(value = "bid")
    public BaseResponse<Bid> bid(@RequestBody Bid bid, Authentication auth) {
        bid.setUid((Long) auth.getPrincipal());
        return new ResultResponse<>(orderService.bid(bid));
    }

    @PutMapping(value = "confirm")
    public BaseResponse<Bid> confirm(@RequestBody Bid bid, Authentication auth) {
        Order order = bid.getOrder();
        order.setUser((User) auth.getDetails());
        return new ResultResponse<>(orderService.confirm(bid));
    }

    @PutMapping(value = "pay")
    public BaseResponse<Order> pay(@RequestBody Order order, Authentication auth) {
        order.setUser((User) auth.getDetails());
        return new ResultResponse<>(orderService.pay(order));
    }

    @PutMapping(value = "finish")
    @PreAuthorize("hasRole('VENDOR')")
    public BaseResponse<Order> finish(@RequestBody Order order, Authentication auth) {
        Vendor vendor = userService.findVendorByUser((User) auth.getDetails());
        order.setVendor(vendor);
        return new ResultResponse<>(orderService.finish(order));
    }
}
