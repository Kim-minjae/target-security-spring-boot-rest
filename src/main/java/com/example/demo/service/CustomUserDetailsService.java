package com.example.demo.service;

import com.example.demo.entity.SecurityMember;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        return
                Optional.ofNullable(memberRepository.findByUemail(uname))
                        .filter(m -> m!= null)
                        .map(m -> new SecurityMember(m)).get();
    }
}
