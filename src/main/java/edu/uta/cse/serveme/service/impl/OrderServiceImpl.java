package edu.uta.cse.serveme.service.impl;

import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.repository.OrderRepository;
import edu.uta.cse.serveme.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return null;
    }
}
