package com.dodam.hotel.util;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingObj {
	
	// 현재 페이지
	private int nowPage;
	// 시작 페이지
	private int startPage;
	// 끝 페이지
	private int endPage;
	// 게시글 총 개수
	private int total;
	// 페이지당 글 개수
	private int cntPerPage;
	// 마지막 페이지
	private int lastPage;
	// 쿼리에 쓸 start, end
	private int start;
	
	private int end;
	
	private int cntPage = 5;
	
	public PagingObj(int total, int nowPage, int cntPerPage) {
		this.total = total;
		this.cntPerPage = cntPerPage;
		this.nowPage = nowPage;
		calcLastPage(this.total, this.cntPerPage);
		calcStartEndPage(this.nowPage, this.cntPage);
		calcStartEnd(this.nowPage, this.cntPerPage);
	}
	
	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int cntPerPage) {
		this.lastPage = (int)Math.ceil((double)total / (double)cntPerPage);
	}
	
	// 시작, 끝 페이지 계산
	public void calcStartEndPage(int nowPage, int cntPage) {
		this.endPage = (int)Math.ceil(((double)nowPage / (double)cntPage) * cntPage);
		if(this.endPage < cntPage) {
			this.endPage = cntPage;
		}
		if(this.lastPage < this.endPage) {
			this.endPage = this.lastPage;
		}
		this.startPage = (this.endPage - cntPage + 1);
		if(this.startPage < 1) {
			this.startPage = 1;
		}
	}
	
	// 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int nowPage, int cntPerPage) {
		this.end = nowPage * cntPerPage;
		this.start = this.end - cntPerPage + 1;
	}
	
}
