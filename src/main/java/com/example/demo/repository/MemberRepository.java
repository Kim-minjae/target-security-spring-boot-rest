package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT a FROM Member a WHERE a.uemail = :uemail")
    Member findByUemail(@Param("uemail")String uemail);

    @Query(value = "SELECT a FROM Member a WHERE a.uname = :uname")
    Member findByUname(@Param("uname")String uname);
}
