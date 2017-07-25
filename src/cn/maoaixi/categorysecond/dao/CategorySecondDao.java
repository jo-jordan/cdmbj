package cn.maoaixi.categorysecond.dao;

import java.util.List;

import cn.maoaixi.categorysecond.vo.CategorySecond;

public interface CategorySecondDao {
	public List<?> findCSById(Integer cid);

	public List<CategorySecond> findAllCS();

	public void deleteCategorySecondByCsid(Integer csid);
	
	public CategorySecond findCategorySecondByCsid(Integer csid);

	public void saveCategorySecond(CategorySecond categorySecond);

	public void updateCategorySecond(CategorySecond categorySecond);
}
