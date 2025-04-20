package org.kosa.mini.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/board")
public class BoardController {
	
	@Autowired
	BoardService bs;
	
	@GetMapping("/getBoard")
	public List<BoardVO> getBoard(String board){
		String [] boardList = {"board1", "board2", "board3", "board4"}; // 게시판 종류
		board = board.trim();
		if(board == null || board.equals("")) return null;
		for(String tmp : boardList) {
			if(tmp.equals(board)) return bs.getBoard(board);
		}
		
		return null;
	}

}
