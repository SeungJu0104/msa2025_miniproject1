package org.kosa.mini.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kosa.mini.util.PageResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
