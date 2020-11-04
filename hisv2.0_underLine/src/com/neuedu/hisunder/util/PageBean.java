package com.neuedu.hisunder.util;

import java.util.List;

public class PageBean<T> {

	private int currentPage = 1;  //当前页
	
	private int pageSize = 10;  //每页记录数
	
	private int totalCount;  //总记录数
	
	private int totalPage;  //总页数
	
	private int beginIndex;  //每一页的开始索引
	
	private List<T> pageData;  //每页的数据

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	//总页数
	public int getTotalPage() {
		return getTotalCount() % getPageSize() == 0 ? getTotalCount() / getPageSize()
				: getTotalCount() / getPageSize() + 1;
	}

	//每一页的开始索引
	public int getBeginIndex() {
		return (getCurrentPage() -1) * getPageSize();
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
	
}
