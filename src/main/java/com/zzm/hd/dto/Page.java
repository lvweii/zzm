/*
 * 系统名称：
 * 模块名称：
 * 描述：分页包装类
 * 作者：DELL
 * version 1.0
 * time  2009-12-4 下午02:12:48
 * copyright Anymusic Ltd.
 */
package com.zzm.hd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author terry
 */
public class Page<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_PAGE_SIZE = 10;
	private int pageSize;
	// 返回的记录
	private List<T> items;
	// 总数量
	private int totalCount;
	private int[] indexes = new int[0];
	private int startIndex = 0;

	public Page(List<T> items, int totalCount) {
		setPageSize(DEFAULT_PAGE_SIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);
	}

	public Page(List<T> items, int totalCount, int startIndex) {
		setPageSize(DEFAULT_PAGE_SIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	public Page(List<T> items, int totalCount, int pageSize, int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	/**
	 * 将页码转换为列表的startIndex，默认PAGESIZE
	 */
	public int convertFromPageToStartIndex(int pageNo) {
		return (pageNo - 1) * DEFAULT_PAGE_SIZE;
	}

	/**
	 * 将页码转换为列表的startIndex
	 */
	public int convertFromPageToStartIndex(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置数据总数，并计算各页起始位置
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			// 页数
			int pageCount = totalCount / pageSize;
			// 页数＋1
			if (totalCount % pageSize > 0)
				pageCount++;
			// 创建页数数组
			indexes = new int[pageCount];
			// 记录下每页的开始记录的index
			for (int i = 0; i < pageCount; i++) {
				indexes[i] = pageSize * i;
			}
		} else {
			this.totalCount = 0;
		}
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int getStartIndex() {
		return startIndex;
	}

	/**
	 * 设置当前起始位置
	 */
	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
	}

	/**
	 * 获得下页起始位置
	 */
	public int getNextIndex() {
		/*
		 * int nextIndex = getStartIndex() + pageSize; if (nextIndex >=
		 * totalCount) return getStartIndex(); else return nextIndex;
		 */
		return getStartIndex() + pageSize;
	}

	/**
	 * 获得上页起始位置
	 */
	public int getPreviousIndex() {
		int previousIndex = getStartIndex() - pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	/**
	 * 取总页数.
	 */
	public long getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return startIndex / pageSize + 1;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount() - 1;
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}
}
