package edu.uta.cse.serveme.entity;

import lombok.Data;

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

    private String sex;

    private String job;

    private String state;

    private Date birthday;

    private String fcmToken;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> role;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }
}