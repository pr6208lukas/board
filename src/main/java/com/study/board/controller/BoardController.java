package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write") //localhost:8080/board/write 으로 접속하면 보내줄 화면 선택...
    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {

        boardService.write(board);

        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {

        //데이터를 담아서 사용자가 보는 페이지로 전달

        /*
        boardService 서비스클래스에서 boardList 매소드가 실행할 경우 해당 메소드에서 반환되는
        List를 "List" 라는 속성이름을 붙여서 넘긴다.
         */

        model.addAttribute("List", boardService.boardList());
        return "boardlist";
    }

    ;

    // localhost:8080/board/view?idboard=1
    //Request 파라미터 사용법 숙지할것
    //현재 적용된 방식으로 상세페이지 열어보기
//    @GetMapping("/board/view")
//    public String boardview(Model model, HttpServletRequest request){
//        model.addAttribute("board", boardService.BoardView(request));
//        return "boardview";
//
//    }
// Spring 3.2 부터 RequestParam의 명칭을 명시 하지 않으면 파라미터 설정오류가 발생된다.
    @GetMapping("/board/view")
    public String boardview(Model model, @RequestParam("idboard") Integer idboard){

        model.addAttribute("board", boardService.BoardView(idboard));
        return "boardview";
    }

}
