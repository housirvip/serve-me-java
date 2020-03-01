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
    Order findOrderById(Long id);

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
     * find order where equal param id and uid
     *
     * @param id  Long
     * @param uid Long
     * @return Order
     */
    Order findOrderByIdAndUid(Long id, Long uid);

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
