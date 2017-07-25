package cn.maoaixi.categorysecond.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import cn.maoaixi.category.service.CategoryService;
import cn.maoaixi.category.vo.Category;
import cn.maoaixi.categorysecond.service.CategorySecondService;
import cn.maoaixi.categorysecond.vo.CategorySecond;

@SuppressWarnings("serial")
public class CategorySecondAction extends ActionSupport{
	/**************** 自动压栈区 **************/
	private List<CategorySecond> categorySecondList;
	public List<CategorySecond> getCategorySecondList() {
		return categorySecondList;
	}
	
	private List<Category> categoryList;
	public List<Category> getCategoryList(){
		return categoryList;
	}

	/**************** 自动压栈区end **************/


	/**************** 注入service区 **************/
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	

	/**************** 注入service区end **************/

	/**************** 特殊字段提供区 **************/
	private Integer csid;
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	private String csname;
	private Integer cid;
	public void setCsname(String csname) {
		this.csname = csname;
	}
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	
	/**************** 特殊字段提供区end **************/





	/**************** action方法区 **************/
	@SuppressWarnings("unchecked")
	public String manage() {
		categorySecondList = categorySecondService.findAllCS();
		categoryList = (List<Category>) categoryService.select();
		return "manageSuccess";
	}
	
	//根据二级分类id删除二级分类
	public String deleteCategorySecondByCsid(){
		categorySecondService.deleteCategorySecondByCsid(csid);
		return "deleteCategorySecondByCsidSuccess";
	}
	
	//增加二级分类
	public String addCategorySecond(){
		CategorySecond categorySecond = new CategorySecond();
		Category category = categoryService.findCategoryByCid(cid);
		categorySecond.setCsname(csname);
		categorySecond.setCategory(category);
		categorySecondService.saveCategorySecond(categorySecond);
		return "addCategorySecondSuccess";
	}
	//修改
	public String modifyCategorySecond(){
		CategorySecond categorySecond = categorySecondService.findCategorySecondByCsid(csid);
		categorySecond.setCsid(csid);
		categorySecond.setCsname(csname);
		categorySecondService.updateCategorySecond(categorySecond);
		return "modifyCategorySecondSuccess";
	}
	
	
	/**************** action方法区end **************/



	/**************** 特殊方法区 **************/
	/**************** 特殊方法区end **************/
}
