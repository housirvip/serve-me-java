package edu.uta.cse.serveme.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
@Entity
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    private String fcmToken;

    private Date createTime;

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