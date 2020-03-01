package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.Bid;
import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.service.OrderService;
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

    @GetMapping(value = "/{id}")
    public BaseResponse<Order> get(@PathVariable Long id, Authentication auth) {
        return new ResultResponse<>(orderService.findOrderByIdAndUid(id, (Long) auth.getPrincipal()));
    }

    @PostMapping(value = "")
    public BaseResponse<Order> create(@RequestBody Order order, Authentication auth) {
        order.setUid((Long) auth.getPrincipal());
        return new ResultResponse<>(orderService.create(order));
    }

    @PutMapping(value = "")
    public BaseResponse<Order> update(@RequestBody Order order, Authentication auth) {
        order.setUid((Long) auth.getPrincipal());
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
        order.setUid((Long) auth.getPrincipal());
        return new ResultResponse<>(orderService.confirm(bid));
    }

    @PutMapping(value = "pay")
    public BaseResponse<Order> pay(@RequestBody Order order, Authentication auth) {
        order.setUid((Long) auth.getPrincipal());
        return new ResultResponse<>(orderService.pay(order));
    }

    @PutMapping(value = "finish")
    @PreAuthorize("hasRole('VENDOR')")
    public BaseResponse<Order> finish(@RequestBody Order order, Authentication auth) {
        order.setVid((Long) auth.getPrincipal());
        return new ResultResponse<>(orderService.finish(order));
    }
}
