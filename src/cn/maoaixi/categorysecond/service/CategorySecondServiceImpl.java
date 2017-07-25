package cn.maoaixi.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.maoaixi.categorysecond.dao.CategorySecondDao;
import cn.maoaixi.categorysecond.vo.CategorySecond;
@Transactional
public class CategorySecondServiceImpl implements CategorySecondService {
	/**************** 注入dao区 **************/
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	/**************** 注入dao区end **************/

	@Override
	public List<?> findCSById(Integer cid) {

		return categorySecondDao.findCSById(cid);
	}

	@Override
	public List<CategorySecond> findAllCS() {
			
		return categorySecondDao.findAllCS();
	}

	@Override
	public void deleteCategorySecondByCsid(Integer csid) {
		categorySecondDao.deleteCategorySecondByCsid(csid);
	}

	@Override
	public void saveCategorySecond(CategorySecond categorySecond) {
		categorySecondDao.saveCategorySecond(categorySecond);
	}

	@Override
	public CategorySecond findCategorySecondByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findCategorySecondByCsid(csid);
	}

	@Override
	public void updateCategorySecond(CategorySecond categorySecond) {
		// TODO Auto-generated method stub
		categorySecondDao.updateCategorySecond(categorySecond);
	}

}
