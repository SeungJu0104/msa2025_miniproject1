package org.kosa.mini.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO bDao;

	public List<BoardVO> getBoard(String board) {
		return bDao.getBoard(board);
	}

}
