package edu.uta.cse.serveme.specification;

import edu.uta.cse.serveme.entity.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nonnull;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class OrderSpecification implements Specification<Order> {

    private Long uid;
    private Long vid;
    private String title;
    private Float priceMin;
    private Float priceMax;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date timeMin;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date timeMax;
    private List<OrderStatus> status;
    private List<VendorCategory> category;
    private String city;
    private String state;

    @Override
    public Predicate toPredicate(@Nonnull Root<Order> root,
                                 @Nonnull CriteriaQuery<?> criteriaQuery,
                                 @Nonnull CriteriaBuilder builder) {
        List<Predicate> list = new ArrayList<>();
        if (uid != null) {
            list.add(builder.equal(root.get("user").get("id"), uid));
        }
        if (vid != null) {
            list.add(builder.equal(root.get("vendor").get("id"), vid));
        }
        if (title != null && !title.isEmpty()) {
            list.add(builder.like(builder.lower(root.get("title")), "%" + title + "%"));
        }
        if (priceMin != null) {
            list.add(builder.greaterThanOrEqualTo(root.get("price"), priceMin));
        }
        if (priceMax != null) {
            list.add(builder.lessThanOrEqualTo(root.get("price"), priceMax));
        }
        if (timeMin != null) {
            list.add(builder.greaterThanOrEqualTo(root.get("time"), timeMin));
        }
        if (timeMax != null) {
            list.add(builder.lessThanOrEqualTo(root.get("time"), timeMax));
        }
        if (status != null && !status.isEmpty()) {
            list.add(builder.in(root.get("status")).value(status));
        }
        if (category != null && !category.isEmpty()) {
            list.add(builder.in(root.get("category")).value(category));
        }
        if (city != null) {
            list.add(builder.equal(root.get("address").get("city"), city));
        }
        if (state != null) {
            list.add(builder.equal(root.get("address").get("state"), state));
        }
        return builder.and(list.toArray(new Predicate[0]));
    }
}
