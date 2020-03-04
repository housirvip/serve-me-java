package edu.uta.cse.serveme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    @JoinColumn
    @JsonIgnoreProperties("bids")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(precision = 18, scale = 2)
    private Float price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    private String description;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }
}