package edu.uta.cse.serveme.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserToken implements Serializable {
    private Integer id;

    private Integer uid;

    private String fcmToken;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}