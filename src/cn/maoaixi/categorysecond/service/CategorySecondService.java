package cn.maoaixi.categorysecond.service;

import java.util.List;

import cn.maoaixi.categorysecond.vo.CategorySecond;

public interface CategorySecondService {
	public List<?> findCSById(Integer cid);

	public List<CategorySecond> findAllCS();

	public void deleteCategorySecondByCsid(Integer csid);

	public void saveCategorySecond(CategorySecond categorySecond);

	public CategorySecond findCategorySecondByCsid(Integer csid);

	public void updateCategorySecond(CategorySecond categorySecond);
}
