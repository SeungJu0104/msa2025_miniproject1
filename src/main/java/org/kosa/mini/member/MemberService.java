package org.kosa.mini.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kosa.mini.board.BoardVO;
import org.kosa.mini.util.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO mDao;
	
	public MemberVO login(MemberVO member) {
		return mDao.login(member);
	}
	
	public int updateFailure(MemberVO dbMember) {
		return mDao.updateFailure(dbMember);
	}

	public int updateLockYn(MemberVO dbMember) {
		return mDao.updateLockYn(dbMember);
	}
	
	public int lockYn(String memberNo, String lockYn) {
		return mDao.lockYn(memberNo, lockYn.charAt(0));
	}

	public PageResponseVO<MemberVO> getMemberList(String searchValue, int pageNo, int size, int parserPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", (pageNo-1) * size + 1);
		map.put("end", pageNo * size);
		map.put("searchValue", searchValue);
		System.out.println(map.toString());
		
		return new PageResponseVO<MemberVO>(pageNo, mDao.getMemberList(map), mDao.getTotalCount(map), size, parserPage);
	}	

}
