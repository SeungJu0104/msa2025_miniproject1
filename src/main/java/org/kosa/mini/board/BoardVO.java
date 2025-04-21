package org.kosa.mini.board;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BoardVO {
	private int postNo; // 게시글번호
	private int boardNo; // 게시판번호
	private int memberNo; // 회원번호
	private String title; // 제목
	private String content; // 내용
	private String boardName; // 게시판명
	private int views; // 조회수
	private String id; // 작성자 아이디
	private Date createdAt; // 작성 일시
	private char deleteYn; // 삭제 여부
	private Date deletedAt; // 삭제 일시
	private int parentNo; // 부모글 번호
	
	@Override
	public String toString() {
		return "BoardVO [postNo=" + postNo + ", boardNo=" + boardNo + ", memberNo=" + memberNo + ", title=" + title
				+ ", content=" + content + ", boardName=" + boardName + ", views=" + views + ", id=" + id
				+ ", createdAt=" + createdAt + ", deleteYn=" + deleteYn + ", deletedAt=" + deletedAt + ", parentNo="
				+ parentNo + "]";
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public char getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(char deleteYn) {
		this.deleteYn = deleteYn;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public int getParentNo() {
		return parentNo;
	}

	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	
}
