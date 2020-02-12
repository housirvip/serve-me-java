package edu.uta.cse.serveme.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uid;

    private String avatar;

    private String sex;

    private String job;

    private String state;

    private Date birthday;

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