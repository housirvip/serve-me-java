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
@Table(name = "`address`")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    private String state;

    private String city;

    private String name;

    private String phone;

    private Integer zipCode;

    private String street;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }
}