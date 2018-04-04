package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public Optional<Board> getById(Long id) { return boardRepository.findById(id);}

    public Iterable<Board> listAllBoards() { return boardRepository.findAll();}
}
