package edu.uta.cse.serveme.specification;

import edu.uta.cse.serveme.entity.Bid;
import edu.uta.cse.serveme.entity.Order;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.annotation.Nonnull;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class BidSpecification implements Specification<Bid> {

    private Long uid;
    private Long orderId;
    private Float priceMin;
    private Float priceMax;

    @Override
    public Predicate toPredicate(@Nonnull Root<Bid> root,
                                 @Nonnull CriteriaQuery<?> criteriaQuery,
                                 @Nonnull CriteriaBuilder builder) {
        List<Predicate> list = new ArrayList<>();
        if (uid != null) {
            list.add(builder.equal(root.get("uid"), uid));
        }
        if (orderId != null) {
            Order order = new Order();
            order.setId(orderId);
            list.add(builder.equal(root.get("order"), order));
        }
        if (priceMin != null) {
            list.add(builder.greaterThanOrEqualTo(root.get("price"), priceMin));
        }
        if (priceMax != null) {
            list.add(builder.lessThanOrEqualTo(root.get("price"), priceMax));
        }
        return builder.and(list.toArray(new Predicate[0]));
    }
}
