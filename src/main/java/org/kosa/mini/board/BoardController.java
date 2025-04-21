package org.kosa.mini.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kosa.mini.paging.PageResponseVO;
import org.kosa.mini.paging.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

 
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bs;
	
	private String [] boards = {"kleague", "bundesliga", "laliga", "freeboard", "epl"}; // 게시판 종류
	Set <String> boardList = new HashSet<String>(Arrays.asList(boards)); // 비교 위한 반복문 사용 피하기위해 Set으로 변환
	
	@GetMapping("/getBoard")
	public String getBoard(String board, String pageNo, String size, String searchValue, RedirectAttributes rd){
		System.out.println("board : " + board + " pageNo : " + pageNo + " size : " + size + " searchValue : " + searchValue);
		Map<String, PageResponseVO> map = new HashMap<String, PageResponseVO>();
		String [] val = {pageNo, size};
		
		for(String tmp : val) {
			if(tmp == null || (tmp.trim()).isEmpty()) { // 검증
				bs.list(searchValue, Util.parseInt(pageNo, 1), Util.parseInt(size, 10));
			}
		}
	
		if(boardList.contains(board)) {
			map.put("list", bs.getBoard(board));
			rd.addFlashAttribute("pageResponse", map);
			return "redirect:/board/goBoard";
		}else {
			errorMsg(rd);
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/goBoard")
	public String goBoard() {
		return "board/boardList";
	}
	
	public static void errorMsg(RedirectAttributes rd) {
		rd.addFlashAttribute("errorMsg", "잘못된 접근입니다.");
	}

}
