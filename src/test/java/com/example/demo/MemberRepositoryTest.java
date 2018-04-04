package com.example.demo;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberRole;
import com.example.demo.repository.MemberRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertTest() {
        for(int i=0; i<100; i++) {
            Member member = new Member();
            member.setUid("user" + i);
            member.setUname("hero " + i);
            member.setUpw("pw" + i);
            member.setUemail("hihi@" + i);
            MemberRole role = new MemberRole();
            if(i <= 80) {
                role.setRoleName("BASIC");
            }else if(i <= 90) {
                role.setRoleName("MANAGER");
            }else {
                role.setRoleName("ADMIN");
            }
            member.setRoles(Arrays.asList(role));
            memberRepository.save(member);
        }
    }

    @Test
    public void testMember() {
        Optional<Member> result = (memberRepository.findById(85L));
        System.out.println("this is the result" + result.toString());
        result.ifPresent(member -> log.info("member " + member));
        result.ifPresent(member -> System.out.println("member" + member));
    }
}