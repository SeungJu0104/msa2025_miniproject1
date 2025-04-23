package org.kosa.mini.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.kosa.mini.board.BoardController;
import org.kosa.mini.util.PageResponseVO;
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
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@PostMapping("login")
	@ResponseBody
	public Map <String, Object> login(@RequestBody MemberVO member) {
		Map <String, Object> map = new HashMap<String, Object>();
		String key = "status", value = "���̵�, ��й�ȣ�� Ȯ�����ּ���.";
//		���̵�, ��й�ȣ �α��� ����
//		if(member.getId() == null || member.getPassword() == null 
//			|| !Validate.isValid(idRegex, member.getId()) || !Validate.isValid(pwRegex, member.getPassword())) 
//			return putMsg(key, value, map);

		MemberVO dbMember = ms.login(member);

		if(dbMember == null || !member.getId().equals(dbMember.getId())) return putMsg(key, value, map);
		if(dbMember.getLoginFailure() == 5 && "N".equals(dbMember.getLockYn())) {
			ms.updateLockYn(dbMember);
			return putMsg(key, "�α����� 5ȸ �̻� �õ��ϼ̽��ϴ�. �����ڿ��� �������ּ���.", map);
		}
		if(dbMember.getLoginFailure() == 5 || "Y".equals(dbMember.getLockYn()))	return putMsg(key, "�α����� 5ȸ �̻� �õ��ϼ̽��ϴ�. �����ڿ��� �������ּ���.", map);
		if(!dbMember.getPassword().equals(member.getPassword())) { // ���̵�� ��ġ�ϳ� ��й�ȣ�� ����ġ�� ��� �α��� ���� Ƚ���� 1 ������Ų��.
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
		if(member == null || member.getId() == null || !Validate.isValid(idRegex, member.getId())) return putMsg("status", "���̵�� 8���̸�, �����ڿ� ���ڸ� �����ؾ� �մϴ�.", map);
		if(ms.getMember(member) != null) {
			map.put("msg", "�ش� ���̵�� ��� �Ұ��մϴ�.");
			return putMsg("status", "ok", map);
		}
		return putMsg("status", "�ش� ���̵�� ��� �����մϴ�.", map);
	
	}
	
	@GetMapping("registerForm")
	public String registerForm() {
		return "/member/memberRegister";
	}
	
	@PostMapping("register")
	public String register(MemberVO member, RedirectAttributes rd){
		if(ms.register(member) == 1) {
			//rd.addAttribute("errorMsg", "ȸ�������� ���ϵ帳�ϴ�. �α������ּ���.");
			return errorMsg(rd);
			//return "redirect:/";
		}
		rd.addAttribute("errorMsg", "�ٽ� �õ����ּ���.");
		return "redirect:/registerForm";
//		Map<String, Object> map = new HashMap<String, Object>();
//		log.info(member.toString());
//		if(ms.register(member) == 1) {
//			map.put("msg", "ȸ�������� ���ϵ帳�ϴ�. �α������ּ���.");
//			return putMsg("status", "ok", map);
//		}
//		return putMsg("status", "�ٽ� �õ����ּ���.", map);
	}
	
	@GetMapping("memberList")
	public String getMemberList(Model model, String searchValue, String pageNo, String size) {
		String parserPage = "5"; // �ϴ� ��Ʈ�ѷ����� �ϵ� �ڵ����� �� ����.
		int sizeDefaultVal = 10;
		int pageNoDefaultVal = 1;
		int parserDefaultVal = 5;
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
		return putMsg("status", "�ٽ� �õ����ּ���.", map);
	}
	
	@GetMapping("getMember")
	public String getMember(@RequestParam String id, Model model, RedirectAttributes rd){
		if(id.isBlank()) {
			//rd.addFlashAttribute("errorMsg", "�ٽ� �õ����ּ���.");
			return errorMsg(rd);
			//return "redirect:/";
		}
		MemberVO dbMember = ms.getInfo(id);
		if(dbMember == null) {
			//rd.addFlashAttribute("errorMsg", "�ٽ� �õ����ּ���.");
			return errorMsg(rd);
			//return "redirect:/";
		}
		model.addAttribute("member", dbMember);
		return "/member/memberUpdate";
		
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(member == null || member.getId().isBlank()) return putMsg("status", "�ٽ� �õ����ּ���.", map);
//		MemberVO dbMember = ms.getInfo(member);
//		if(dbMember != null) {
//			session.setAttribute("member", dbMember);
//			map.put("status", "ok");
//			return map;
//		}
//		return putMsg("status", "�ٽ� �õ����ּ���.", map);
	}
	
	@PostMapping("updateMember")
	public String updateMember(MemberVO member, RedirectAttributes rd){
		System.out.println("a");
		if(member == null) {
			return errorMsg(rd);
			//return "redirect:/";
		}

		if(ms.updateMember(member) == 1) return "redirect:/";
		return errorMsg(rd);
		//return "redirect:/";
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		System.out.println(member.toString());
//		if(member == null || member.getId().isBlank()) model.addAttribute("errorMsg", "�ٽ� �õ����ּ���.");
//		if(ms.updateMember(member) == 1) {
//			return ;
//		}
//		return putMsg("status", "�ٽ� �õ����ּ���.", map);
	}
	
	@PostMapping("deleteMember")
	@ResponseBody
	public Map<String, Object> deleteMember(@RequestBody MemberVO member, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		if(member == null || member.getId().isBlank()) return putMsg("status", "�ٽ� �õ����ּ���.", map);
		if(ms.deleteMember(member) == 1) {
			map.put("status", "ok");
			session.removeAttribute("member");
			return map;
		}
		return putMsg("status", "�ٽ� �õ����ּ���.", map);
	}
	
	@GetMapping("detailForm")
	public String detailForm (@RequestParam String id, Model model, RedirectAttributes rd) {
		if(id == null || id.isBlank()) {
			return errorMsg(rd);
			//return "redirect:/";
		}
		MemberVO dbMember = ms.getInfo(id);
		if(dbMember == null) {
			return errorMsg(rd);
			//return "redirect:/";
		}
		model.addAttribute("item", dbMember);
		return "/member/memberDetail";
	}
	
	public static Map <String, Object> putMsg(String key, String value, Map<String, Object> map){
		map.put(key, value);
		return map;
	}
	
	public static String errorMsg(RedirectAttributes rd) {
		rd.addFlashAttribute("errorMsg", "�ٽ� �õ����ּ���.");
		return "redirect:/";
	}

}
