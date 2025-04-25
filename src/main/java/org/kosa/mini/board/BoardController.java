package org.kosa.mini.board;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
	
	private String [] boards = {"K����", "�е�������", "�󸮰�", "�����Խ���", "EPL"};
	private Set <String> boardList = new HashSet<String>(Arrays.asList(boards));
	private String parserPage = "5"; // ����¡ ���� ũ��� �ϴ� ��Ʈ�ѷ����� �ϵ� �ڵ����� ����.
	private int sizeDefaultVal = 10, pageNoDefaultVal = 1, parserDefaultVal = 5;
	
//	���������� board ���� ����� ���� ���
//	private String [] boards = {"kleague", "bundesliga", "laliga", "freeboard", "epl"}; // �Խ��� ����
//	private String [] mappers = {"K����", "�е�������", "�󸮰�", "�����Խ���", "EPL"}; // ���� mapper
//	private List <String> boardList = new ArrayList<String>(Arrays.asList(boards));
//	private List <String> mapperList = new ArrayList<String>(Arrays.asList(mappers));
//	private Map<String, String> boardMapper = boardMapper(boardList, mapperList);
	
	@GetMapping("/getBoard")
	public String getBoard(String board, String pageNo, String size, String searchValue, Model model, RedirectAttributes rd){
		if(board == null || board.trim().isBlank()) return errorMsg(rd);			
		if(boardList.contains(board)) {
			model.addAttribute("pageResponse", bs.getBoard(board, searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
			return "/board/boardList";
		}else {
			return errorMsg(rd);
		}
		
//		���������� board ���� ����� ���� ���
//		if(mapperList.contains(board)){
//			model.addAttribute("pageResponse", bs.getBoard(board, searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
//			return "/board/boardList";
//		}
//		if(boardMapper.containsKey(board)) {
//			if(boardMapper.get(board) != null) board = boardMapper.get(board); // mapper�� �ѱ� �� �����´�.
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
			return MemberController.putMsg("status", "�ٽ� �õ����ּ���.", map);
		if(post.getPassword().trim().length() > 4) return MemberController.putMsg("status", "��й�ȣ�� 4�ڸ��Դϴ�.", map);
		if(!post.getPassword().equals(bs.getPassword(post))) return MemberController.putMsg("status", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", map);
		if(bs.deletePost(post) != 1) return MemberController.putMsg("status", "�ٽ� �õ����ּ���.", map);
		map.put("msg", "�Խñ��� �����ƽ��ϴ�.");
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

//		���������� board ���� ����� ���� ���
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
		if(post == null || post.getTitle() == null || post.getBoard() == null || post.getBoard().trim().isBlank()) MemberController.putMsg("status", "�߸��� �����Դϴ�.", map);
		if(post.getTitle().trim().isBlank()) return MemberController.putMsg("status", "������ �Է����ּ���.", map);
		if(bs.registerPost(post) != 1) return MemberController.putMsg("status", "�ٽ� �õ����ּ���.", map);
		map.put("msg", "�Խñ��� ��ϵƽ��ϴ�.");
		return MemberController.putMsg("status", "ok", map);
	}

//	���������� board ���� ����� ���� ���
//	public Map<String, String> boardMapper(List<String> boardList, List<String> mapperList){
//		Map<String, String> boardNameMap = new HashMap<String, String>();
//		if(boardList.size() != mapperList.size()) return null;
//		for(int i = 0; i < boardList.size(); i++) boardNameMap.put(boardList.get(i), mapperList.get(i));
//		return boardNameMap;
//	}
	
	public static String errorMsg(RedirectAttributes rd) {
		rd.addFlashAttribute("errorMsg", "�߸��� �����Դϴ�.");
		return "redirect:/";
	}
	
	public static String errorMsg2(RedirectAttributes rd, String board, int size) {
		rd.addFlashAttribute("errorMsg", "�ٽ� �õ����ּ���.");
		return url(board, size);
	}
	
	public static String url(String board, int size) {
		return "redirect:/board/getBoard?board=" + board + "&pageNo=1&size=" + size + "&searchValue=";
	}

}
