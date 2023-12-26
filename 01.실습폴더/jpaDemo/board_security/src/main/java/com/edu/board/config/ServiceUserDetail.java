package com.edu.board.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class ServiceUserDetail implements UserDetails {
    
    private Long seq;
    private String loginId;
    private String password;
    private Collection<GrantedAuthority> authorities; // 권한
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 실습에서 권한은 별도로 존재하지 않아 null 처리
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
