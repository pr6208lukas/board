package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    // 서비스 클래스와 연결...
    @Autowired
    private BoardService boardService;

    //작성 페이지로 이동하기
    @GetMapping("/board/write") //localhost:8080/board/write 으로 접속하면 보내줄 화면 선택...
    public String boardWriteForm() {

        return "boardwrite";
    }

    // 작성된 내용 저장 처리
    @PostMapping("/board/writepro")
    public String boardWritePro(Board board) {

        //작성된 내용을 저장하기
        boardService.write(board);
        return "boardlist";
    }

    // 목록 화면으로 이동하기
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


    // localhost:8080/board/view?idboard=1

    // 작성 된 글의 상세 내용 화면으로 이동하기
    // Spring 3.2 부터 RequestParam의 명칭을 명시 하지 않으면 파라미터 설정오류가 발생된다.
    @GetMapping("/board/view")
    //데이터를 RequestParm(쿼리 string) 을 이용하여 넘기는 방법 적용....
    public String boardview(Model model, @RequestParam("idboard") Integer idboard){

        model.addAttribute("board", boardService.BoardView(idboard));
        return "boardview";
    }

    // 삭제 처리
    @GetMapping("/board/delete")
    //데이터를 RequestParm(쿼리 string) 을 이용하여 넘기는 방법 적용....
    public String boardDelete(@RequestParam("idboard") Integer idboard){

        boardService.boardDelete(idboard);

        //목록으로 돌아가기
        return "redirect:/board/list";
    }

    // 수정 화면으로 이동
    @GetMapping("/board/modify/{idboard}")
    //데이터를 PathVariable 을 이용하여 넘기는 방법 적용....
    public String boardModify(Model model,@PathVariable("idboard") Integer idboard){

        model.addAttribute("board", boardService.BoardView(idboard));
        return "boardmodify";
    }

    //업데이트 저장
    @PostMapping("/board/update/{idboard}")
    //데이터를 PathVariable 을 이용하여 넘기는 방법 적용....
    public String boardUpdate(@PathVariable("idboard") Integer idboard, Board board){

        //기존에 작성된 게시글 가져오기
        Board boardTemp = boardService.BoardView(idboard);

        //수정한 내용을 적용하기
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        //적용된 내용을 저장하기
        boardService.write(boardTemp);

        //목록 화면으로 돌아가기
        return "redirect:/board/list";
    }
}
