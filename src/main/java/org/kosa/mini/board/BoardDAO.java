package org.kosa.mini.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDAO {

	public List<BoardVO> getBoard(Map <String, Object> map);
	public int getTotalCount(Map<String, Object> map);
	public BoardVO getPost(BoardVO post);
	public int addViewCnt(int postNo);

}
