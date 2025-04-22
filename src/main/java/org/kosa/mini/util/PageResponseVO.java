package org.kosa.mini.util;

import java.util.List;

import lombok.Data;

@Data
public class PageResponseVO<T> {
	
	private String board = null;
	private List<T> list = null;  
	private int totalCount = 0; 
	private int totalPage = 0; 
	private int startPage = 0; 
	private int endPage = 0;
	private int pageNo = 0;
	private int size = 10;
	private int parserPage = 5;
	
	public PageResponseVO(String board, int pageNo, List<T> list, int totalCount, int size, int parserPage) {
		this.board = board;
//		this.totalCount = totalCount;
//		this.pageNo = pageNo;
//		this.list = list;
//		this.size = size;
//		this.parserPage = parserPage;
//
//		totalPage =  (int)Math.ceil((double)totalCount / size);	   
//		startPage = ((pageNo - 1) / parserPage) * parserPage + 1;
//		endPage = ((pageNo - 1) / parserPage) * parserPage + parserPage;
//		if (endPage > totalPage) endPage = totalPage;
		calc(pageNo, list, totalCount, size, parserPage);

	}
	
	public PageResponseVO(int pageNo, List<T> list, int totalCount, int size, int parserPage) {
//		this.totalCount = totalCount;
//		this.pageNo = pageNo;
//		this.list = list;
//		this.size = size;
//		this.parserPage = parserPage;
//
//		totalPage =  (int)Math.ceil((double)totalCount / size);	   
//		startPage = ((pageNo - 1) / parserPage) * parserPage + 1;
//		endPage = ((pageNo - 1) / parserPage) * parserPage + parserPage;
//		if (endPage > totalPage) endPage = totalPage;
		calc(pageNo, list, totalCount, size, parserPage);
	}
	
	public void calc(int pageNo, List<T> list, int totalCount, int size, int parserPage) {
		this.totalCount = totalCount;
		this.pageNo = pageNo;
		this.list = list;
		this.size = size;
		this.parserPage = parserPage;

		totalPage =  (int)Math.ceil((double)totalCount / size);	   
		startPage = ((pageNo - 1) / parserPage) * parserPage + 1;
		endPage = ((pageNo - 1) / parserPage) * parserPage + parserPage;
		if (endPage > totalPage) endPage = totalPage;
	}

	public boolean isPrev() {
		return startPage != 1; 
	}
	
	public boolean isNext() {
		return totalPage != endPage;		
	}

}
