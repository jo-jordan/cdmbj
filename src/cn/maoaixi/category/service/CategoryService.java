package cn.maoaixi.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.maoaixi.category.vo.Category;
@Transactional
public interface CategoryService {
	public List<?> select();

	public void deleteCategoryByCid(Integer cid);

	public void saveCategory(Category category);

	public void updateCategroy(Category c);
	
	public Category findCategoryByCid(Integer cid);

}
