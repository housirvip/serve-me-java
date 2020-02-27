package edu.uta.cse.serveme.entity;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private Integer points;

    private String firebaseUid;

    private String photoUrl;

    private String gender;

    private String fcmToken;

    @Enumerated(EnumType.STRING)
    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    private List<UserRole> role;

    @Enumerated(EnumType.STRING)
    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(targetClass = VendorCategory.class, fetch = FetchType.EAGER)
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