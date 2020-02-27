package edu.uta.cse.serveme.service;

import edu.uta.cse.serveme.entity.Order;

/**
 * @author housirvip
 */
public interface OrderService {

    /**
     * select a order where equal param id
     *
     * @param id Long
     * @return User
     */
    Order findOrderById(Long id);

    /**
     * update user where equal param user
     *
     * @param order Order
     * @return Order
     */
    Order update(Order order);
}
