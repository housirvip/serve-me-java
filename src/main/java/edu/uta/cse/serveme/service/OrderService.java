package edu.uta.cse.serveme.service;

import edu.uta.cse.serveme.entity.Bid;
import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
     * find orders where equal param user
     *
     * @param user     User
     * @param pageable Pageable
     * @return List
     */
    Page<Order> findOrdersByUser(User user, Pageable pageable);

    /**
     * find orders where equal param user
     *
     * @param specification Specification
     * @param pageable      Pageable
     * @return List
     */
    Page<Order> findOrders(Specification<Order> specification, Pageable pageable);

    /**
     * find orders where equal param user
     *
     * @param specification Specification
     * @param pageable      Pageable
     * @return List
     */
    Page<Bid> findBids(Specification<Bid> specification, Pageable pageable);

    /**
     * bid to an order
     *
     * @param bid Bid
     * @return Bid
     */
    Bid bid(Bid bid);

    /**
     * confirm an order, use bid and user to verify access
     *
     * @param bid  Bid
     * @param user User
     * @return Bid
     */
    Bid confirm(Bid bid, User user);

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

    /**
     * find bid by bid id
     *
     * @param id Long
     * @return Bid
     */
    Bid findBidById(Long id);
}
