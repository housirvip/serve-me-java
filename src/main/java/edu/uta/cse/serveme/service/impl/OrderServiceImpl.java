package edu.uta.cse.serveme.service.impl;

import edu.uta.cse.serveme.base.ErrorMessage;
import edu.uta.cse.serveme.entity.*;
import edu.uta.cse.serveme.repository.BidRepository;
import edu.uta.cse.serveme.repository.OrderRepository;
import edu.uta.cse.serveme.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BidRepository bidRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException(ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Order create(Order order) {
        order.setId(null);
        order.setVendor(null);
        order.setStatus(OrderStatus.Biding);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        orderRepository.findByIdAndUser(order.getId(), order.getUser()).ifPresentOrElse(
                result -> orderRepository.save(order),
                () -> {
                    throw new RuntimeException(ErrorMessage.ORDER_NOT_FOUND);
                });
        return order;
    }

    @Override
    public Order findOrder(Order order) {
        return orderRepository.findByIdAndUser(order.getId(), order.getUser()).orElseThrow(() -> new RuntimeException(ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Page<Order> findOrdersByUser(User user, Pageable pageable) {
        return orderRepository.findByUser(user, pageable);
    }

    @Override
    public Page<Order> findOrders(Specification<Order> specification, Pageable pageable) {
        return orderRepository.findAll(specification, pageable);
    }

    @Override
    public Page<Bid> findBids(Specification<Bid> specification, Pageable pageable) {
        return bidRepository.findAll(specification, pageable);
    }

    @Override
    public Bid bid(Bid bid) {
        bid.setId(null);
        return bidRepository.save(bid);
    }

    @Override
    public Bid confirm(Bid bid) {
        bidRepository.findById(bid.getId()).ifPresentOrElse(
                b -> {
                    bid.setUid(b.getUid());
                    bid.setPrice(b.getPrice());
                },
                () -> {
                    throw new RuntimeException(ErrorMessage.BID_NOT_FOUND);
                }
        );
        Order order = bid.getOrder();
        orderRepository.findByIdAndUser(order.getId(), order.getUser()).ifPresentOrElse(
                o -> {
                    Vendor vendor = new Vendor();
                    vendor.setId(bid.getUid());
                    o.setVendor(vendor);
                    o.setPrice(bid.getPrice());
                    o.setStatus(OrderStatus.Pending);
                    orderRepository.save(o);
                }, () -> {
                    throw new RuntimeException(ErrorMessage.ORDER_NOT_FOUND);
                }
        );
        return null;
    }

    @Override
    public Order pay(Order order) {
        orderRepository.findByIdAndUser(order.getId(), order.getUser()).ifPresentOrElse(
                o -> {
                    o.setStatus(OrderStatus.Processing);
                    orderRepository.save(o);
                }, () -> {
                    throw new RuntimeException(ErrorMessage.ORDER_NOT_FOUND);
                }
        );
        return order;
    }

    @Override
    public Order finish(Order order) {
        orderRepository.findByIdAndVendor(order.getId(), order.getVendor()).ifPresentOrElse(
                o -> {
                    o.setStatus(OrderStatus.Finished);
                    orderRepository.save(o);
                }, () -> {
                    throw new RuntimeException(ErrorMessage.ORDER_NOT_FOUND);
                }
        );
        return order;
    }
}
