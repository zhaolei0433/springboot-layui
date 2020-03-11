package com.example.security.userdetails;

import com.example.entity.SysUserInfo;
import com.example.global.constants.SystemDefines;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

/**
 * @author zhaolei
 * Create: 2019/11/19 11:35
 * Modified By:
 * Description: 用户登录验证
 */
public class MyUserDetails implements UserDetails {
    private Integer userId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled; //是否开启
    private boolean isAccountNonExpired; //是否过期

    MyUserDetails(SysUserInfo userInfo, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userInfo.getId();
        this.username = userInfo.getUserName();
        this.password = userInfo.getPassword();
        this.authorities = authorities;
        this.enabled = userInfo.isEnabled();
        this.isAccountNonExpired = (LocalDateTime.parse(userInfo.getExpireDate(), DateTimeFormatter.ofPattern(SystemDefines.SIMPLE_DATE_FORMAT)).isAfter(LocalDateTime.now()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
