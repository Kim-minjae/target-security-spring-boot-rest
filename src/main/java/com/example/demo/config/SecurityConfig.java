package com.example.demo.config;

import com.example.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    //스프링 시큐리티에서 로그인 처리를 구현하려면 SecurityConfig에서
    // AuthenticationManagerBuilder를 주입해서 인증에 대한 처리를 해야 한다.

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //method 를 autowired할때는 그 return 값을 객체로 빈에 저장하는것이 아니었나?
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean  // . authenticationManagerBean 메소드의 경우에는 SpringSecurity에서 사용되는 인증객체를 Bean으로 등록할 때 사용합니다.
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); }


    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**", "/script/**", "image/**", "/fonts/**", "lib/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/login")
                .permitAll() .antMatchers("/user")
                .hasAuthority("USER") .antMatchers("/admin")
                .hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().logout();

    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
