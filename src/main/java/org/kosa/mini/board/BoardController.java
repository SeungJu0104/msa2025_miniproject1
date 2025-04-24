package org.kosa.mini.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kosa.mini.member.MemberController;
import org.kosa.mini.util.PageResponseVO;
import org.kosa.mini.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

 
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bs;
	
	private String [] boards = {"kleague", "bundesliga", "laliga", "freeboard", "epl"}; // 게시판 종류
	private Set <String> boardList = new HashSet<String>(Arrays.asList(boards));
	private String parserPage = "5";
	private int sizeDefaultVal = 10, pageNoDefaultVal = 1, parserDefaultVal = 5;
	
	@GetMapping("/getBoard")
	public String getBoard(String board, String pageNo, String size, String searchValue, Model model, RedirectAttributes rd){
		if(board == null || board.trim().isEmpty()) { // 검증
			return errorMsg(rd);			
		}
		if(boardList.contains(board)) {
			model.addAttribute("pageResponse", bs.getBoard(board, searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
			return "/board/boardList";
		}else {
			return errorMsg(rd);
		}
	}
	
	@PostMapping("/goBoard")
	public String goBoard() {
		return "board/boardList";
	}
	
	@GetMapping("getPost")
	public String getPost(BoardVO post, RedirectAttributes rd, Model model) {
		System.out.println(post.getBoard());
		System.out.println(post.getPostNo());
		if(post == null || post.getBoard().isBlank() || post.getBoard() == null) {
			MemberController.errorMsg(rd);
		}
		if(post.getPostNo() < 1) {
			return errorMsg2(rd, post.getBoard());
		}
		BoardVO dbPost = bs.getPost(post);
		if(dbPost == null || bs.addViewCnt(dbPost.getPostNo()) != 1) {
			return errorMsg2(rd, post.getBoard());
		}
		model.addAttribute("post", dbPost);
		return "/board/boardDetail";
	}
	
	@GetMapping("updateForm")
	public String updateForm(BoardVO post, RedirectAttributes rd, Model model) {
		System.out.println("a");
		System.out.println(post.getBoard());
		if(post == null || post.getBoard().isBlank() || post.getBoard() == null) {
			MemberController.errorMsg(rd);
		}
		if(post.getPostNo() < 1) {
			return errorMsg2(rd, post.getBoard());
		}
		BoardVO dbPost = bs.getPost(post);
		if(dbPost == null) {
			return errorMsg2(rd, post.getBoard());
		}
		model.addAttribute("post", dbPost);
		return "/board/boardUpdate";
		
	}
	
	@PostMapping("updatePost")
	public String updatePost(BoardVO post, RedirectAttributes rd, Model model) {
		System.out.println(post.getTitle() + post.getWriter() + post.getContent() + post.getBoard());
		if(post == null || post.getBoard().isBlank() || post.getBoard() == null) {
			MemberController.errorMsg(rd);
		}
		if(post.getPostNo() < 1 || post.getTitle().isBlank() || post.getWriter().isBlank() || post.getContent().isBlank() || bs.updatePost(post) != 1) {
			return errorMsg2(rd, post.getBoard());
		}
		model.addAttribute("post", bs.getPost(post));
		return "/board/boardDetail";
	}
	
	// fetch
	@GetMapping("deletePost")
	public String deletePost(String postNo) {
		
		System.out.println(postNo);
		
		
		return null;
	}
	
	public int parserNum(String postNo) {
		try {
			int res = Integer.parseInt(postNo);
			return res;
		} catch(Exception e){
			return -1;
		}
	}
	
	public String errorMsg(RedirectAttributes rd) {
		rd.addFlashAttribute("errorMsg", "잘못된 접근입니다.");
		return "redirect:/";
	}
	
	public String errorMsg2(RedirectAttributes rd, String board) {
		rd.addFlashAttribute("errorMsg", "다시 시도해주세요.");
		return "redirect:/board/getBoard?board=" + board + "&pageNo=1&size=10&searchValue=";
	}

}
