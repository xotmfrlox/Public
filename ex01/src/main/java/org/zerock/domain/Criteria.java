//파라미터가 여러 개로 늘어나면 관리하기 어려워지기 때문에 아예 클래스로 만들어서 객체로 처리하는 것이 바람직하다.
package org.zerock.domain;

public class Criteria {

	private int page;
	private int perPageNum;
	
	//생성자 new 인스턴스
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		//예외처리
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;

	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {

			this.perPageNum = 10;
		} else {
			this.perPageNum = perPageNum;

		}
	}

	public int getPage() {
		return page;
	}

	// method for MyBatis SQL Mapper -
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

	// method for mybatis sql mapper
	public int getPerPageNum() {
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + "," + "perPageNum=" + perPageNum + "]";
	}

}
