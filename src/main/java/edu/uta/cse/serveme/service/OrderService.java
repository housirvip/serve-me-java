package edu.uta.cse.serveme.service;

import edu.uta.cse.serveme.entity.Bid;
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
    Order findById(Long id);

    /**
     * create order where equal param user
     *
     * @param order Order
     * @return Order
     */
    Order create(Order order);

    /**
     * update order where equal param user
     *
     * @param order Order
     * @return Order
     */
    Order update(Order order);

    /**
     * find order where equal param order
     *
     * @param order Order
     * @return Order
     */
    Order findOrder(Order order);

    /**
     * bid to an order
     *
     * @param bid Bid
     * @return Bid
     */
    Bid bid(Bid bid);

    /**
     * confirm an order
     *
     * @param bid Bid
     * @return Bid
     */
    Bid confirm(Bid bid);

    /**
     * pay to the order
     *
     * @param order Order
     * @return Order
     */
    Order pay(Order order);

    /**
     * finish the order
     *
     * @param order Order
     * @return Order
     */
    Order finish(Order order);
}
