package cn.maoaixi.utils;

import java.util.List;

/**
 * 管理分页
 * 
 * @author lzjlxebr
 *
 */
public class PageBean<T> {
	//当前页码
	private Integer page;
	//每页显示的数量
	private Integer limit;
	//总记录数
	private Integer totalCount;
	//总页数
	private Integer totalPage;
	//商品集合
	private List<T> productList;
	public Integer getPage() {
	
		return page;
	}
	public void setPage(Integer page) {
	
		this.page = page;
	}
	public Integer getLimit() {
	
		return limit;
	}
	public void setLimit(Integer limit) {
	
		this.limit = limit;
	}
	public Integer getTotalCount() {
	
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
	
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
	
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
	
		this.totalPage = totalPage;
	}
	public List<T> getProductList() {
	
		return productList;
	}
	public void setProductList(List<T> productList) {
	
		this.productList = productList;
	}
	
	
}
