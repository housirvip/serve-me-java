package edu.uta.cse.serveme.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfo implements Serializable {
    private Integer id;

    private Integer uid;

    private String avatar;

    private String sex;

    private String job;

    private String state;

    private Date birthday;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}