package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "uid")
public class Member {

    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false, unique = true, length=50)
    private String uid;

    @Column(nullable = false, length=200)
    private String upw;

    @Column(nullable = false, unique = true, length=50)
    private String uemail;

    @Column(nullable = false, unique = true, length=50)
    private String uname;

    @CreationTimestamp
    private Date regdate;

    @UpdateTimestamp
    private Date updatedate;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="uid")
    private List<MemberRole> roles;

}
