package cn.maoaixi.category.service;

import java.util.List;

import cn.maoaixi.category.dao.CategoryDao;
import cn.maoaixi.category.vo.Category;

public class CategoryServiceImpl implements CategoryService{
	//注入分级dao
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//调用dao中方法即可
	public List<?> select(){
		return categoryDao.select();
	}

	@Override
	public void deleteCategoryByCid(Integer cid) {
		categoryDao.deleteCategoryByCid(cid);
	}

	@Override
	public void saveCategory(Category category) {
		categoryDao.saveCategory(category);
	}

	@Override
	public void updateCategroy(Category c) {
		categoryDao.updateCategroy(c);
	}

	@Override
	public Category findCategoryByCid(Integer cid) {
		return categoryDao.findCategoryByCid(cid);
	}
}
