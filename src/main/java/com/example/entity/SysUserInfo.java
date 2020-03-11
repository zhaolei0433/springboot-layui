package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @author zhaolei
 * Create: 2020/2/25 12:12
 * Modified By:
 * Description: 用户信息表
 */
@Entity
@Table(name = "sys_user_info")
@DynamicUpdate()
@DynamicInsert()
public class SysUserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 相当于identity主键生成策略
    private Integer id;

    @Column(name = "user_name", nullable = false, columnDefinition = "varchar(100) COMMENT '用户名'")
    private String userName;

    @Column(name = "password", nullable = false, columnDefinition = "varchar(100) COMMENT '密码'")
    private String password;

    @Column(name = "phone", nullable = false, columnDefinition = "varchar(100) COMMENT '电话号码'")
    private String phone;

    @Column(name = "mail_box", nullable = false, columnDefinition = "varchar(100) COMMENT '邮箱'")
    private String mailBox;

    @Column(name = "create_time", nullable = false, columnDefinition = "varchar(100) COMMENT '创建时间'")
    private String createTime;

    @Column(name = "user_type", nullable = false, columnDefinition = "varchar(100) COMMENT '用户类型'")
    private String userType;

    @Column(name = "enabled", nullable = false, columnDefinition = "varchar(100) COMMENT '是否开启'")
    private boolean enabled;

    @Column(name = "expire_date", nullable = false, columnDefinition = "varchar(100) COMMENT '是否开启'")
    private boolean expireDdate;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "sys_user_role_rel",
            joinColumns = {@JoinColumn(name = "sys_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<RoleInfo> roleInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Set<RoleInfo> getRoleInfos() {
        return roleInfos;
    }

    public void setRoleInfos(Set<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "SysUserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", mailBox='" + mailBox + '\'' +
                ", createTime='" + createTime + '\'' +
                ", userType='" + userType + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
