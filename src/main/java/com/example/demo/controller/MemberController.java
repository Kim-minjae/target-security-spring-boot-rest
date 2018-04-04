package com.example.demo.controller;

import com.example.demo.domain.AuthenticationRequest;
import com.example.demo.domain.AuthenticationToken;
import com.example.demo.entity.Member;
import com.example.demo.entity.MemberRole;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public AuthenticationToken login(

            @RequestBody AuthenticationRequest authenticationRequest, HttpSession session ) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        UserDetails user = customUserDetailsService.loadUserByUsername(username);  // 예제에선 유저 객체에서 직접 이름과 authorities와 id를 가져왔는데 details에서 가져오면 상관없지않을까?

        return new AuthenticationToken(user.getUsername(), user.getAuthorities(), session.getId());
    }


    @PostMapping("")
    public String create(Member member) {
        MemberRole role = new MemberRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setUpw(passwordEncoder.encode(member.getUpw()));
        role.setRoleName("BASIC");
        member.setRoles(Arrays.asList(role));
        memberRepository.save(member);
        return "redirect:/";
    }
}
