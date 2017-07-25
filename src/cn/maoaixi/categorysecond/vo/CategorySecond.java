package cn.maoaixi.categorysecond.vo;

import java.util.HashSet;
import java.util.Set;

import cn.maoaixi.category.vo.Category;
import cn.maoaixi.product.vo.Product;

public class CategorySecond {
	private Integer csid;
	//二级分类所属一级分类
	private Category category;
	private String csname;
	private Set<Product> products = new HashSet<Product>();
	public Integer getCsid() {
	
		return csid;
	}
	public void setCsid(Integer csid) {
	
		this.csid = csid;
	}
	public Category getCategory() {
	
		return category;
	}
	public void setCategory(Category category) {
	
		this.category = category;
	}
	public String getCsname() {
	
		return csname;
	}
	public void setCsname(String csname) {
	
		this.csname = csname;
	}
	public Set<Product> getProducts() {
	
		return products;
	}
	public void setProducts(Set<Product> products) {
	
		this.products = products;
	}

	

}
