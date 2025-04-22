package org.kosa.mini.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberService ms;
	
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
//			|| !Validate.isValid("^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,10}$", member.getId()) || 
//			!Validate.isValid("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]{8,12}$", member.getPassword())) 
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
		//if(member.getId() == null || !Validate.isValid("^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,10}$", member.getId())) return putMsg("status", "���̵�� 8���̸�, �����ڿ� ���ڸ� �����ؾ� �մϴ�.", map);
		MemberVO dbMember = ms.login(member);
		if(dbMember != null) {
			map.put("msg", "�ش� ���̵�� ��� �Ұ��մϴ�.");
			return putMsg("status", "ok", map);
		}
		else return putMsg("status", "�ش� ���̵�� ��� �����մϴ�.", map);
	}
	
	@GetMapping("registerForm")
	public String updateForm() {
		return "/member/memberRegister";
	}
	
	@GetMapping("memberList")
	public String getMemberList(Model model, String searchValue, String pageNo, String size) {
		System.out.println(" pageNo : " + pageNo + " size : " + size + " searchValue : " + searchValue);
		String parserPage = "5"; // �ϴ� ��Ʈ�ѷ����� �ϵ� �ڵ����� �� ����.
		int sizeDefaultVal = 10;
		int pageNoDefaultVal = 1;
		int parserDefaultVal = 5;
		model.addAttribute("pageResponse", ms.getMemberList(searchValue, Util.parseInt(pageNo, pageNoDefaultVal), Util.parseInt(size, sizeDefaultVal), Util.parseInt(parserPage, parserDefaultVal)));
		return "/member/memberList";
	}
	
	@PostMapping("lockYn")
	@ResponseBody
	public Map<String, Object> lockYn(@RequestBody Map<String, String> req) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(ms.lockYn(req.get("memberNo"), req.get("lockYn")) == 1) return putMsg("status", "ok", map);
		return putMsg("status", "�ٽ� �õ����ּ���.", map);
	}
	

	
	
	public static Map <String, Object> putMsg(String key, String value, Map<String, Object> map){
		map.put(key, value);
		return map;
	}

}
