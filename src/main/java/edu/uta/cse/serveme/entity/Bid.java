package edu.uta.cse.serveme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
@Entity
@Table(name = "`bid`")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    private Integer price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    @JoinColumn
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }
}