package com.edu.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.board.domain.User;
import com.edu.board.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceUserDetailService implements UserDetailsService {
    
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findById(username);
        ServiceUserDetail serviceUserDetail = new ServiceUserDetail();
        serviceUserDetail.setLoginId(user.getUserId());
        serviceUserDetail.setPassword(user.getPassword());
        return serviceUserDetail;
    } 
}
