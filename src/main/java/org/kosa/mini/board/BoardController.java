package org.kosa.mini.board;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kosa.mini.member.MemberController;
import org.kosa.mini.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	private String [] boards = {"K리그", "분데스리가", "라리가", "자유게시판", "EPL"};
	private Set <String> boardList = new HashSet<String>(Arrays.asList(boards));
	private String parserPage = "5"; // 페이징 개수 크기는 일단 컨트롤러에서 하드 코딩으로 전달.
	private int sizeDefaultVal = 10, pageNoDefaultVal = 1, parserDefaultVal = 5;
	
//	브라우저에서 board 값을 영어로 받을 경우
//	private String [] boards = {"kleague", "bundesliga", "laliga", "freeboard", "epl"}; // 게시판 종류
//	private String [] mappers = {"K리그", "분데스리가", "라리가", "자유게시판", "EPL"}; // 종류 mapper
//	private List <String> boardList = new ArrayList<String>(Arrays.asList(boards));
//	private List <String> mapperList = new ArrayList<String>(Arrays.asList(mappers));
//	private Map<String, String> boardMapper = boardMapper(boardList, mapperList);
	
	@GetMapping("/getBoard")
	public String getBoard(String board, String pageNo, String size, String searchValue, Model model, RedirectAttributes rd){
		log.info(board);
		if(board == null || board.trim().isBlank()) return errorMsg(rd);			
		if(boardList.contains(board)) {
			model.addAttribute("pageResponse", bs.getBoard(board, searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
			return "/board/boardList";
		}else {
			return errorMsg(rd);
		}
		
//		브라우저에서 board 값을 영어로 받을 경우
//		if(mapperList.contains(board)){
//			model.addAttribute("pageResponse", bs.getBoard(board, searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
//			return "/board/boardList";
//		}
//		if(boardMapper.containsKey(board)) {
//			if(boardMapper.get(board) != null) board = boardMapper.get(board); // mapper로 한글 값 가져온다.
//			model.addAttribute("pageResponse", bs.getBoard(board, searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
//			return "/board/boardList";
//		}else {
//			return errorMsg(rd);
//		}
	}
	
	@GetMapping("getPost")
	public String getPost(BoardVO post, RedirectAttributes rd, Model model) {
		if(post == null || post.getBoard().isBlank() || post.getBoard() == null) MemberController.errorMsg(rd);
		if(post.getPostNo() < 1) return errorMsg2(rd, post.getBoard(), sizeDefaultVal);
		BoardVO dbPost = bs.getPost(post);
		if(dbPost == null || bs.addViewCnt(dbPost.getPostNo()) != 1) return errorMsg2(rd, post.getBoard(), sizeDefaultVal);
		dbPost = bs.getPost(post);
		model.addAttribute("post", dbPost);
		return "/board/boardDetail";
	}
	
	@GetMapping("updateForm")
	public String updateForm(BoardVO post, RedirectAttributes rd, Model model) {
		if(post == null || post.getBoard().isBlank() || post.getBoard() == null) MemberController.errorMsg(rd);
		if(post.getPostNo() < 1) return errorMsg2(rd, post.getBoard(), sizeDefaultVal);
		BoardVO dbPost = bs.getPost(post);
		if(dbPost == null) return errorMsg2(rd, post.getBoard(), sizeDefaultVal);
		model.addAttribute("post", dbPost);
		return "/board/boardUpdate";
	}
	
	@PostMapping("updatePost")
	public String updatePost(BoardVO post, RedirectAttributes rd, Model model) {
		if(post == null || post.getBoard().isBlank() || post.getBoard() == null) MemberController.errorMsg(rd);
		if(post.getPostNo() < 1 || post.getTitle().isBlank()
			|| post.getContent().isBlank() || bs.updatePost(post) != 1)
			return errorMsg2(rd, post.getBoard(), sizeDefaultVal);
		model.addAttribute("post", bs.getPost(post));
		return "/board/boardDetail";
	}
	
	@PostMapping("deletePost")
	@ResponseBody
	public Map<String, Object> deletePost(@RequestBody BoardVO post) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(post == null || post.getPostNo() < 1 || post.getPassword() == null || post.getPassword().isBlank()) 
			return MemberController.putMsg("status", "다시 시도해주세요.", map);
		if(post.getPassword().trim().length() > 4) return MemberController.putMsg("status", "비밀번호는 4자리입니다.", map);
		if(!post.getPassword().equals(bs.getPassword(post))) return MemberController.putMsg("status", "비밀번호가 일치하지 않습니다.", map);
		if(bs.deletePost(post) != 1) return MemberController.putMsg("status", "다시 시도해주세요.", map);
		map.put("msg", "게시글이 삭제됐습니다.");
		return MemberController.putMsg("status", "ok", map);
	}
	
	@GetMapping("postRegisterForm")
	public String postRegisterForm(String board, Model model, RedirectAttributes rd) {
		if(board == null || board.trim().isBlank() || !boardList.contains(board)) return errorMsg(rd);
		Set<String>others = new HashSet<String>(boardList);
		others.remove(board);
		model.addAttribute("board", board);
		model.addAttribute("others", others);
		return "/board/boardRegister";

//		브라우저에서 board 값을 영어로 받을 경우
//		if(board == null || board.trim().isBlank() || !mapperList.contains(board)) return errorMsg(rd);
//		Set<String>others = new HashSet<String>(mapperList);
//		others.remove(board);
//		model.addAttribute("board", board);
//		model.addAttribute("others", others);
//		return "/board/boardRegister";
	}
	
	@PostMapping("registerPost")
	@ResponseBody
	public Map<String, Object> registerPost(@RequestBody BoardVO post) {
		Map<String, Object> map = new HashMap<String, Object>();
		log.info(post.toString());
		log.info(post.getId());
		if(post == null || post.getTitle() == null || post.getBoard() == null || post.getBoard().trim().isBlank()) MemberController.putMsg("status", "잘못된 접근입니다.", map);
		if(post.getTitle().trim().isBlank()) return MemberController.putMsg("status", "제목을 입력해주세요.", map);
		if(bs.registerPost(post) != 1) return MemberController.putMsg("status", "다시 시도해주세요.", map);
		map.put("msg", "게시글이 등록됐습니다.");
		return MemberController.putMsg("status", "ok", map);
	}
	
	public Map<String, String> boardMapper(List<String> boardList, List<String> mapperList){
		Map<String, String> boardNameMap = new HashMap<String, String>();
		if(boardList.size() != mapperList.size()) return null;
		for(int i = 0; i < boardList.size(); i++) boardNameMap.put(boardList.get(i), mapperList.get(i));
		return boardNameMap;
	}
	
	public String errorMsg(RedirectAttributes rd) {
		rd.addFlashAttribute("errorMsg", "잘못된 접근입니다.");
		return "redirect:/";
	}
	
	public String errorMsg2(RedirectAttributes rd, String board, int size) {
		rd.addFlashAttribute("errorMsg", "다시 시도해주세요.");
		return url(board, size);
	}
	
	public String url(String board, int size) {
		return "redirect:/board/getBoard?board=" + board + "&pageNo=1&size=" + size + "&searchValue=";
	}

}
