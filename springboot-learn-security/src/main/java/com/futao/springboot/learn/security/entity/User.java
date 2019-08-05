package com.futao.springboot.learn.security.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author futao
 * Created on 2019-07-23.
 */
@Getter
@Setter
@Entity //说明这个class是实体类，并且使用默认的orm规则，即class名即数据库表中表名，class字段名即表中的字段名
@Table(name = "security_user")    //如果想改变这种默认的orm规则，就要使用@Table来改变class名与数据库中表名的映射规则，@Column来改变class中字段名与db中表的字段名的映射规则
public class User implements UserDetails {

    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "locked")
    private Boolean locked;

    @Transient
    private List<Role> roleList;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList.stream().map(it -> new SimpleGrantedAuthority(it.getNameEn())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
