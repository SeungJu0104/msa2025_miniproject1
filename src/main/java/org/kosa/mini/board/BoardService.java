package org.kosa.mini.board;

import java.util.HashMap;
import java.util.Map;

import org.kosa.mini.util.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {
	
	@Autowired
	BoardDAO bDao;

	public PageResponseVO<BoardVO> getBoard(String board, String searchValue, int pageNo, int size, int parserPage) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board", board);
		map.put("start", (pageNo-1) * size + 1);
		map.put("end", pageNo * size);
		map.put("searchValue", searchValue);
		return new PageResponseVO<BoardVO>(board, pageNo, bDao.getBoard(map), bDao.getTotalCount(map), size, parserPage);
	}

	public BoardVO getPost(BoardVO post) {
		return bDao.getPost(post);
	}

	public int addViewCnt(int postNo) {
		return bDao.addViewCnt(postNo);
	}

	public int updatePost(BoardVO post) {
		return bDao.updatePost(post);
	}

	public int deletePost(BoardVO post) {
		return bDao.deletePost(post);
	}

	public String getPassword(BoardVO post) {
		return bDao.getPassword(post);
	}

	public int registerPost(BoardVO post) {
		return bDao.registerPost(post);
	}

}
