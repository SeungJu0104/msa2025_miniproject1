package org.kosa.mini.board;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BoardService {
	
	
	BoardDAO bDao;

	public List<BoardVO> getBoard(String board) {
		return bDao.getBoard(board);
	}

}
