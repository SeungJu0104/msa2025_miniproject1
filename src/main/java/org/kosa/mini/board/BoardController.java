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
	
	private String [] boards = {"kleague", "bundesliga", "laliga", "freeboard", "epl"}; // �Խ��� ����
	Set <String> boardList = new HashSet<String>(Arrays.asList(boards)); // �� ���� �ݺ��� ��� ���ϱ����� Set���� ��ȯ
	
	@GetMapping("/getBoard")
	public String getBoard(String board, String pageNo, String size, String searchValue, Model model, RedirectAttributes rd){
		String parserPage = "5"; // �������� �ڵ� �����ϵ��� �ۼ������� �ϴ� ��Ʈ�ѷ����� �ϵ� �ڵ����� �� ����.
		int sizeDefaultVal = 10;
		int pageNoDefaultVal = 1;
		int parserDefaultVal = 5;
		
		if(board == null || board.trim().isEmpty()) { // ����
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
		System.out.println(post.toString());
		if(post == null || post.getPostNo() < 1) {
			return BoardController.errorMsg(rd);
		}
		BoardVO dbPost = bs.getPost(post);
		if(dbPost == null) {
			return BoardController.errorMsg(rd);
		}
		if(bs.addViewCnt(dbPost.getPostNo()) != 1) {
			return BoardController.errorMsg(rd);
		}
		model.addAttribute("post", dbPost);
		return "/board/boardDetail";
	}
	
	@GetMapping("updatePost")
	public String updatePost(@RequestParam("postNo") String postNo) {
		
		System.out.println(postNo);
		
		
		return null;
	}
	
	@GetMapping("deletePost")
	public String deletePost(@RequestParam("postNo") String postNo) {
		
		System.out.println(postNo);
		
		
		return null;
	}
	
	public static String errorMsg(RedirectAttributes rd) {
		rd.addFlashAttribute("errorMsg", "�߸��� �����Դϴ�.");
		return "redirect:/";
	}

}
