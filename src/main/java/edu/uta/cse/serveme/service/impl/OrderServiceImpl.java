package edu.uta.cse.serveme.service.impl;

import edu.uta.cse.serveme.base.ErrorMessage;
import edu.uta.cse.serveme.entity.Bid;
import edu.uta.cse.serveme.entity.Order;
import edu.uta.cse.serveme.entity.OrderStatus;
import edu.uta.cse.serveme.repository.BidRepository;
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
    private final BidRepository bidRepository;

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException(ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Order create(Order order) {
        order.setId(null);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        orderRepository.findByIdAndUid(order.getId(), order.getUid()).ifPresentOrElse(
                result -> orderRepository.save(order),
                () -> {
                    throw new RuntimeException(ErrorMessage.ORDER_NOT_FOUND);
                });
        return order;
    }

    @Override
    public Order findOrderByIdAndUid(Long id, Long uid) {
        return orderRepository.findByIdAndUid(id, uid).orElseThrow(() -> new RuntimeException(ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public Bid bid(Bid bid) {
        bid.setId(null);
        return bidRepository.save(bid);
    }

    @Override
    public Bid confirm(Bid bid) {
        Order order = bid.getOrder();
        bidRepository.findById(bid.getId()).ifPresentOrElse(
                b -> {
                    bid.setUid(b.getUid());
                    bid.setPrice(b.getPrice());
                },
                () -> {
                    throw new RuntimeException(ErrorMessage.BID_NOT_FOUND);
                }
        );
        orderRepository.findByIdAndUid(order.getId(), order.getUid()).ifPresentOrElse(
                o -> {
                    o.setPrice(bid.getPrice());
                    o.setVid(bid.getUid());
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
        orderRepository.findByIdAndUid(order.getId(), order.getUid()).ifPresentOrElse(
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
        orderRepository.findByIdAndVid(order.getId(), order.getVid()).ifPresentOrElse(
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
