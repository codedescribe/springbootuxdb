package com.maxfan.springbootuxdb.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	//limit ?,? 页码计算忽略数 (currentPage - 1) * pageSize, pageSize
	private int currentPage; // 当前页
	private int pageSize;// 页大小，页显示条数
	private int totalRecord;// 总记录数
	private int ignoreNum;//忽略条数
	// 用来保存查到的数据
	private List<T> list = new ArrayList<T>();

	// 构造函数 创建对象时候录入数据
	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.ignoreNum=(currentPage - 1) * pageSize;
	}

	// 得到总页数
	public int getTotalPage() {
		return (totalRecord + pageSize - 1) / pageSize;
	}

	// 上一页
	public int getUpPage() {
		if (currentPage > 1) {
			return currentPage - 1;
		}
		return 1;
	}

	// 下一页
	public int getNextPage() {
		if (currentPage < getTotalPage()) {
			return currentPage + 1;
		}
		return getTotalPage();
	}

	// 首页
	public int firstPage() {
		return 1;
	}

	// 尾页
	public int lastPage() {
		return getTotalPage();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {

		this.list = list;
	}

	public int getIgnoreNum() {
		return ignoreNum;
	}
}
