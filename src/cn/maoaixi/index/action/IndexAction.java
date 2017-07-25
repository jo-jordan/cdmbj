package cn.maoaixi.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.maoaixi.category.service.CategoryService;
import cn.maoaixi.category.vo.Category;
import cn.maoaixi.product.service.ProductService;
import cn.maoaixi.product.vo.Product;

/**
 * 首页访问Action
 * 
 * @author lzjlxebr
 *
 */
@SuppressWarnings("serial")
public class IndexAction extends ActionSupport {
	/****************自动压栈区**************/
	
	
	/****************自动压栈区**************/
	
	
	
	

	/****************注入service区**************/
	// 注入第一分级service层
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 注入商品查询的service层
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/****************注入service区**************/

	// private List<Product> hotProductList;
	// private List<Product> newProductList;
	// public List<Product> getHotProductList() {
	// return hotProductList;
	// }
	// public List<Product> getNewProductList() {
	// return newProductList;
	// }

	/****************action方法区**************/
	@SuppressWarnings("unchecked")
	public String execute() {
		// 查询一级分类
		List<Category> cateList = (List<Category>) categoryService.select();
		// 这里不使用ServletActionContext是因为页面第一次被访问时,数据不会查询到
		ActionContext.getContext().getSession().put("cateList", cateList);

		// 查询热门商品
		List<Product> hotProductList = (List<Product>) productService.selectHotProduct();
		ActionContext.getContext().getSession().put("hotProductList", hotProductList);

		// 查询最新商品
		List<Product> newProductList = (List<Product>) productService.selectNewProduct();
		ActionContext.getContext().getSession().put("newProductList", newProductList);

		System.out.println("一级分类:" + ActionContext.getContext().getSession().get("cateList"));
		System.out.println("热门商品:" + ActionContext.getContext().getSession().get("hotProductList").toString());
		System.out.println("最新商品:" + ActionContext.getContext().getSession().get("newProductList").toString());

		return "indexSuccess";
	}
	/****************action方法区**************/
}
