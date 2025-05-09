package org.kosa.mini.member;

import java.util.HashMap;
import java.util.Map;

import org.kosa.mini.util.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO mDao;
	
	public MemberVO login(MemberVO member) {
		return mDao.login(member);
	}
	
	@Transactional
	public int updateFailure(MemberVO dbMember) {
		return mDao.updateFailure(dbMember);
	}
	
	@Transactional
	public int updateLockYn(MemberVO dbMember) {
		return mDao.updateLockYn(dbMember);
	}
	
	@Transactional
	public int lockYn(String memberNo, String lockYn) {
		return mDao.lockYn(memberNo, lockYn.charAt(0));
	}

	public PageResponseVO<MemberVO> getMemberList(String searchValue, int pageNo, int size, int parserPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageNo-1) * size + 1);
		map.put("end", pageNo * size);
		map.put("searchValue", searchValue);
		return new PageResponseVO<MemberVO>(pageNo, mDao.getMemberList(map), mDao.getTotalCount(map), size, parserPage);
	}

	public MemberVO getMember(MemberVO member) {
		return mDao.getMember(member);
	}
	
	@Transactional
	public int register(MemberVO member) {
		return mDao.register(member);
	}

	public MemberVO getInfo(String id) {
		return mDao.getInfo(id);
	}
	
	@Transactional
	public int updateMember(MemberVO member) {
		return mDao.updateMember(member);
	}
	
	@Transactional
	public int deleteMember(MemberVO member) {
		return mDao.deleteMember(member);
	}
	

}
