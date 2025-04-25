package org.kosa.mini.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.kosa.mini.util.Util;
import org.kosa.mini.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberService ms;
	
	private String idRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,10}$";
	private String pwRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,12}$";
	private String parserPage = "5"; // 페이징 개수 크기는 일단 컨트롤러에서 하드 코딩으로 전달.
	private int sizeDefaultVal = 10, pageNoDefaultVal = 1, parserDefaultVal = 5;
	
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@PostMapping("login")
	@ResponseBody
	public Map <String, Object> login(@RequestBody MemberVO member) {
		Map <String, Object> map = new HashMap<String, Object>();
		String key = "status", value = "아이디, 비밀번호를 확인해주세요.";
//		아이디, 비밀번호 검증
		if(member.getId() == null || member.getPassword() == null 
			|| !Validate.isValid(idRegex, member.getId()) || !Validate.isValid(pwRegex, member.getPassword())) 
			return putMsg(key, value, map);
		MemberVO dbMember = ms.login(member);
		if(dbMember == null || !member.getId().equals(dbMember.getId())) return putMsg(key, value, map);
		if(dbMember.getLoginFailure() == 5 && "N".equals(dbMember.getLockYn())) {
			ms.updateLockYn(dbMember);
			return putMsg(key, "로그인을 5회 이상 시도하셨습니다. 관리자에게 문의해주세요.", map);
		}
		if(dbMember.getLoginFailure() == 5 || "Y".equals(dbMember.getLockYn()))	return putMsg(key, "로그인을 5회 이상 시도하셨습니다. 관리자에게 문의해주세요.", map);
		if(!dbMember.getPassword().equals(member.getPassword())) { // 아이디는 일치하나 비밀번호가 불일치할 경우 로그인 실패 횟수를 1 증가시킨다.
			ms.updateFailure(dbMember);
			return putMsg(key, value, map);
		}
		map.put(key, "ok");
		map.put("id", dbMember.getId());
		map.put("boardAuth", Character.toUpperCase(dbMember.getBoardAuth()));
		return map;
	}
	
	@PostMapping("idDupChk")
	@ResponseBody
	public Map<String, Object> idDupChk(@RequestBody MemberVO member){
		Map<String, Object> map = new HashMap<String, Object>();
		if(member == null || member.getId() == null || !Validate.isValid(idRegex, member.getId())) return putMsg("status", "아이디는 8~10자이며, 영문자와 숫자만 포함해야 합니다.", map);
		if(ms.getMember(member) != null) {
			map.put("msg", "해당 아이디는 사용 불가합니다.");
			return putMsg("status", "ok", map);
		}
		return putMsg("status", "해당 아이디는 사용 가능합니다.", map);
	
	}
	
	@GetMapping("registerForm")
	public String registerForm() {
		return "/member/memberRegister";
	}
	
	@PostMapping("register")
	public String register(MemberVO member, RedirectAttributes rd){
		if(ms.register(member) == 1) {
			rd.addAttribute("errorMsg", "회원가입을 축하드립니다. 로그인해주세요.");
			return "redirect:/";
		}
		rd.addAttribute("errorMsg", "다시 시도해주세요.");
		return "redirect:/registerForm";
		
//		form 대신 fetch를 이용한 방식
//		Map<String, Object> map = new HashMap<String, Object>();
//		log.info(member.toString());
//		if(ms.register(member) == 1) {
//			map.put("msg", "회원가입을 축하드립니다. 로그인해주세요.");
//			return putMsg("status", "ok", map);
//		}
//		return putMsg("status", "다시 시도해주세요.", map);
	}
	
	@GetMapping("memberList")
	public String getMemberList(Model model, String searchValue, String pageNo, String size) {
		model.addAttribute("pageResponse", ms.getMemberList(searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
		return "/member/memberList";
	}
	
	@GetMapping("updateForm")
	public String updateForm() {
		return "/member/memberUpdate";
	}
	
	@PostMapping("lockYn")
	@ResponseBody
	public Map<String, Object> lockYn(@RequestBody Map<String, String> req) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(ms.lockYn(req.get("memberNo"), req.get("lockYn")) == 1) return putMsg("status", "ok", map);
		return putMsg("status", "다시 시도해주세요.", map);
	}
	
	@GetMapping("getMember")
	public String getMember(@RequestParam String id, Model model, RedirectAttributes rd){
		if(id.isBlank()) return errorMsg(rd);
		
		MemberVO dbMember = ms.getInfo(id);
		if(dbMember == null) return errorMsg(rd);
		model.addAttribute("member", dbMember);
		return "/member/memberUpdate";
		
//		form 대신 fetch를 이용한 방식		
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(member == null || member.getId().isBlank()) return putMsg("status", "다시 시도해주세요.", map);
//		MemberVO dbMember = ms.getInfo(member);
//		if(dbMember != null) {
//			session.setAttribute("member", dbMember);
//			map.put("status", "ok");
//			return map;
//		}
//		return putMsg("status", "다시 시도해주세요.", map);
	}
	
	@PostMapping("updateMember")
	public String updateMember(MemberVO member, RedirectAttributes rd){
		if(member == null) return errorMsg(rd);
		if(ms.updateMember(member) == 1) return "redirect:/";
		return errorMsg(rd);

//		form 대신 fetch를 이용한 방식
//		Map<String, Object> map = new HashMap<String, Object>();
//		System.out.println(member.toString());
//		if(member == null || member.getId().isBlank()) model.addAttribute("errorMsg", "다시 시도해주세요.");
//		if(ms.updateMember(member) == 1) {
//			return ;
//		}
//		return putMsg("status", "다시 시도해주세요.", map);
	}
	
	@PostMapping("deleteMember")
	@ResponseBody
	public Map<String, Object> deleteMember(@RequestBody MemberVO member, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		if(member == null || member.getId().isBlank()) return putMsg("status", "다시 시도해주세요.", map);
		if(ms.deleteMember(member) == 1) {
			map.put("status", "ok");
			session.removeAttribute("member");
			return map;
		}
		return putMsg("status", "다시 시도해주세요.", map);
	}
	
	@GetMapping("detailForm")
	public String detailForm (@RequestParam String id, Model model, RedirectAttributes rd) {
		if(id == null || id.isBlank()) return errorMsg(rd);
		MemberVO dbMember = ms.getInfo(id);
		if(dbMember == null) return errorMsg(rd);
		model.addAttribute("item", dbMember);
		return "/member/memberDetail";
	}
	
	public static Map <String, Object> putMsg(String key, String value, Map<String, Object> map){
		map.put(key, value);
		return map;
	}
	
	public static String errorMsg(RedirectAttributes rd) {
		rd.addFlashAttribute("errorMsg", "다시 시도해주세요.");
		return "redirect:/";
	}

}
