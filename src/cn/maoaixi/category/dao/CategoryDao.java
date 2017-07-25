package cn.maoaixi.category.dao;

import java.util.List;

import cn.maoaixi.category.vo.Category;

public interface CategoryDao {
	public List<?> select();

	public void deleteCategoryByCid(Integer cid);
	
	public Category findCategoryByCid(Integer cid);

	public void saveCategory(Category category);

	public void updateCategroy(Category c);
}
