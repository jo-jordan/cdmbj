package cn.maoaixi.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.maoaixi.categorysecond.vo.CategorySecond;

public class CategorySecondDaoImpl extends HibernateDaoSupport implements CategorySecondDao {

	@Override
	public List<?> findCSById(Integer cid) {
			
		return this.getHibernateTemplate().find("from CategorySecond where cid = ?", cid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategorySecond> findAllCS() {
			
		return (List<CategorySecond>) this.getHibernateTemplate().find("from CategorySecond");
	}

	@Override
	public void deleteCategorySecondByCsid(Integer csid) {
		CategorySecond  categorySecond= findCategorySecondByCsid(csid);
		this.getHibernateTemplate().delete(categorySecond);
	}

	@Override
	public CategorySecond findCategorySecondByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	@Override
	public void saveCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	@Override
	public void updateCategorySecond(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
	

}
