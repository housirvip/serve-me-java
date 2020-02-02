package edu.uta.cse.serveme.entity;

import edu.uta.cse.serveme.validator.ChangePass;
import edu.uta.cse.serveme.validator.Login;
import edu.uta.cse.serveme.validator.Register;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable {
    private Integer id;

    @NotBlank(groups = {Login.class, Register.class})
    private String username;

    @NotBlank(groups = {Register.class})
    private String email;

    @NotBlank(groups = {Register.class})
    private String phone;

    @NotBlank(groups = {Login.class, Register.class, ChangePass.class})
    private String password;

    private Date createTime;

    private Date updateTime;

    private Boolean enable;

    private Integer level;

    private List<String> role;

    private UserInfo userInfo;

    private static final long serialVersionUID = 1L;
}