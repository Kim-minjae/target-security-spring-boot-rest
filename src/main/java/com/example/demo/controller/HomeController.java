package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    BoardService boardService;

    @RequestMapping(path = "/Board")
    public Iterable<Board> boardList() { return boardService.listAllBoards();}

    @RequestMapping(path = "/Board/{id}")
    public Optional<Board> getById(@PathVariable(value = "id") Long id){
        return boardService.getById(id);
    }

}
