package edu.uta.cse.serveme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "`user`")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String phone;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updateTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer points;

    private String firebaseUid;

    private String photoUrl;

    private String gender;

    private String fcmToken;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Address address;

    @Enumerated(EnumType.STRING)
    @Fetch(value = FetchMode.SUBSELECT)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    private List<UserRole> role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Vendor vendor;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }
}