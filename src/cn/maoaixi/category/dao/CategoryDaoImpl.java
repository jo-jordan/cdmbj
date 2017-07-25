package cn.maoaixi.category.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.maoaixi.category.vo.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao{

	@Override
	public List<?> select() {
		List<?> list = this.getHibernateTemplate().find("from Category");
		return list;
	}

	@Override
	public void deleteCategoryByCid(Integer cid) {
		Category category = findCategoryByCid(cid);
		this.getHibernateTemplate().delete(category);
	}

	@Override
	public Category findCategoryByCid(Integer cid) {
			
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	@Override
	public void saveCategory(Category category) {
		this.getHibernateTemplate().save(category);
	}

	@Override
	public void updateCategroy(Category c) {
		this.getHibernateTemplate().update(c);
	}

}
