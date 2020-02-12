package edu.uta.cse.serveme.entity;

import edu.uta.cse.serveme.validator.ChangePass;
import edu.uta.cse.serveme.validator.Login;
import edu.uta.cse.serveme.validator.Register;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(groups = {Register.class})
    private String username;

    @NotBlank(groups = {Login.class, Register.class})
    private String email;

    @NotBlank(groups = {Register.class})
    private String phone;

    @NotBlank(groups = {Login.class, Register.class, ChangePass.class})
    private String password;

    private Date createTime;

    private Date updateTime;

    private Boolean enable;

    private Integer level;

    @ElementCollection
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