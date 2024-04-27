package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    //글 작성 처리
    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board){

        boardRepository.save(board);
    }
    public List<Board> boardList(){

        //"Board" 클래스에 담긴 목록(List)를 반환
        return boardRepository.findAll();
    }

    //작성된 게시글 보여주기
    public Board BoardView(Integer idboard){

        return boardRepository.findById(idboard).get();
    }
}
