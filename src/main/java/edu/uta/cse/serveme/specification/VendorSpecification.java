package edu.uta.cse.serveme.specification;

import edu.uta.cse.serveme.entity.Vendor;
import edu.uta.cse.serveme.entity.VendorCategory;
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
public class VendorSpecification implements Specification<Vendor> {

    private String name;
    private Integer rateMin;
    private Integer rateMax;
    private Float priceMin;
    private Float priceMax;
    private List<VendorCategory> categories;
    private String city;
    private String state;

    @Override
    public Predicate toPredicate(@Nonnull Root<Vendor> root,
                                 @Nonnull CriteriaQuery<?> criteriaQuery,
                                 @Nonnull CriteriaBuilder builder) {
        List<Predicate> list = new ArrayList<>();
        if (priceMin != null) {
            list.add(builder.greaterThanOrEqualTo(root.get("price"), priceMin));
        }
        if (priceMax != null) {
            list.add(builder.lessThanOrEqualTo(root.get("price"), priceMax));
        }
        if (rateMin != null) {
            list.add(builder.greaterThanOrEqualTo(root.get("rate"), rateMin));
        }
        if (rateMax != null) {
            list.add(builder.lessThanOrEqualTo(root.get("rate"), rateMax));
        }
        if (categories != null && !categories.isEmpty()) {
            List<Predicate> tmp = new ArrayList<>();
            categories.forEach(c -> tmp.add(builder.isMember(c, root.get("categories"))));
            list.add(builder.or(tmp.toArray(new Predicate[0])));
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
