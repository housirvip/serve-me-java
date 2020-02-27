package edu.uta.cse.serveme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
@Entity
@Table(name = "`vendor`")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String name;

    private String email;

    private String phone;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateTime;

    private String address;

    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(targetClass = VendorCategory.class, fetch = FetchType.LAZY)
    private List<VendorCategory> categories;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }
}