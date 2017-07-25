package cn.maoaixi.category.action;

import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import cn.maoaixi.category.service.CategoryService;
import cn.maoaixi.category.vo.Category;

@SuppressWarnings("serial")
public class CategoryAction extends ActionSupport {
	/**************** 自动压栈区 **************/
	private List<Category> category;

	public List<Category> getCategory() {
		return category;
	}
	
	/**************** 自动压栈区end **************/


	/**************** 注入service区 **************/
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**************** 注入service区end **************/

	/**************** 特殊字段提供区 **************/
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	private String cname;
	
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**************** 特殊字段提供区end **************/


	/**************** action方法区 **************/
	//删除一级分类
	public String deleteCategoryByCid() {
		categoryService.deleteCategoryByCid(cid);
		return "deleteCategoryByCidSuccess";
	}
	
	//查询所有一级分类
	@SuppressWarnings("unchecked")
	public String manage(){
		category = (List<Category>) categoryService.select();
		return "manageSuccess";
	}
	
	//添加一级分类
	@SuppressWarnings("unchecked")
	public String addCategory(){
		Category _category = new Category();
		_category.setCname(cname);
		
		categoryService.saveCategory(_category);
		category = (List<Category>) categoryService.select();
		this.addActionMessage("添加成功!");
		return "addCategorySuccess";
	}
	
	//修改一级分类
	@SuppressWarnings("unchecked")
	public String modifyCategory(){
		category = (List<Category>) categoryService.select();
		Iterator<Category> it = category.iterator();
		while(it.hasNext()){
			Category c = it.next();
			if(c.getCid().equals(cid)){
				c.setCid(cid);
				c.setCname(cname);
				categoryService.updateCategroy(c);
			}
		}
		category = (List<Category>) categoryService.select();
		this.addActionMessage("修改成功!");
		return NONE;
	}
	/**************** action方法区end **************/

	/**************** 特殊方法区 **************/
	/**************** 特殊方法区end **************/
}
