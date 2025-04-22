package org.kosa.mini.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberDAO {
	
	public MemberVO login(MemberVO member);
	public int updateFailure(MemberVO dbMember);
	public int updateLockYn(MemberVO dbMember);
	public List<MemberVO> getMemberList(Map<String, Object> map);
	public int getTotalCount(Map<String, Object> map);
	public int lockYn(@Param("memberNo") String memberNo, @Param("lockYn") char lockYn);

}
