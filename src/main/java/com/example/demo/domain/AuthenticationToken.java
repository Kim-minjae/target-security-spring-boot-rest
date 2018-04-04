package com.example.demo.domain;

import java.util.Collection;

//.client 에게 리턴해줄 값을 모아놓는 클래스, 접속한 사용자는 누구며, 권한은 이런것이 있다, 앞으로 요청할때는 token 값을 가지고 요청하라 이런의미.
public class AuthenticationToken {

    private String username;
    private Collection authorites;
    private String token;

    public AuthenticationToken(String username, Collection authorites, String token) {
        this.username = username;
        this.authorites = authorites;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection getAuthorites() {
        return authorites;
    }

    public void setAuthorites(Collection authorites) {
        this.authorites = authorites;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
