package org.kosa.mini.board;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BoardVO {
	private int postNo; // �Խñ۹�ȣ
	private int boardNo; // �Խ��ǹ�ȣ
	private int memberNo; // ȸ����ȣ
	private String title; // ����
	private String content; // ����
	private String board; // �Խ��Ǹ�
	private int views; // ��ȸ��
	private String id; // �ۼ��� ���̵�
	private Date createdAt; // �ۼ� �Ͻ�
	private char deleteYn; // ���� ����
	private Date deletedAt; // ���� �Ͻ�
	private int parentNo; // �θ�� ��ȣ
	private String writer; // �ۼ��ڸ�
	private String password; // ��й�ȣ
	private String searchValue; // �˻���
	private String boardName; // �Խ��� ����
}
