package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private Date createDate;

    @Column
    private String content;

    public Board(){

    }

    public Board(String title, String writer, Date createDate, String content) {
        this.title = title;
        this.writer = writer;
        this.createDate = createDate;
        this.content = content;
    }
}
