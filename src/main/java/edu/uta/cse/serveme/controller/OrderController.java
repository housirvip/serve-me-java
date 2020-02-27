package edu.uta.cse.serveme.controller;

import edu.uta.cse.serveme.base.BaseResponse;
import edu.uta.cse.serveme.base.ResultResponse;
import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/{id}")
    public BaseResponse<Order> getOrder(@PathVariable Long id) {
        return new ResultResponse<>(orderService.findOrderById(id));
    }
}
