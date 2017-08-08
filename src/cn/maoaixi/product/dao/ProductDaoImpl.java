package cn.maoaixi.product.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.maoaixi.product.vo.Product;
import cn.maoaixi.utils.PageHibernateUtils;

public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao{

	@Override
	public List<?> selectHotProduct() {
		return (List<?>) this.getHibernateTemplate().execute(new PageHibernateUtils<>("from Product where is_hot=?",new Object[]{1},0,10));
	}

	@Override
	public List<?> selectNewProduct() {
		return (List<?>) this.getHibernateTemplate().execute(new PageHibernateUtils<>("from Product order by pdate desc",null,0,10));
	}


	/**
	 * 获取到所有一级分类的商品总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer findCountByCid(Integer cid) {
		String hql = "select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, cid);
		return list.get(0).intValue();
	}

	/**
	 * 查询一级分类下所有商品,并分页显示
	 */
	@Override
	public List<Product> findByPage(int start, int limit, Integer cid) {
		String hql = "select p from Product p,CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		return (List<Product>) this.getHibernateTemplate().execute(new PageHibernateUtils<Product>(hql,new Object[]{cid},start,limit));
	}

	/**
	 * 查询二级分类下所有商品总和
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, csid);
		return list.get(0).intValue();
	}

	/**
	 * 查询二级分类下所有商品,并分页显示
	 */
	@Override
	public List<Product> findByPageCsid(int start, int limit, Integer csid) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		return (List<Product>) this.getHibernateTemplate().execute(new PageHibernateUtils<Product>(hql,new Object[]{csid},start,limit));
	}

	@Override
	public Product findProductByPid(Integer pid) {
		return (Product) this.getHibernateTemplate().get(Product.class, pid);
	}

	/**
	 * 查询所有商品,分页显示
	 */
	@Override
	public List<Product> findAllProductByPage(int start, int limit) {
		String hql = "from Product";
		return this.getHibernateTemplate().execute(new PageHibernateUtils<Product>(hql,null,start,limit));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getAllProductCount() {
		String hql = "select count(*) from Product";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		return list.get(0).intValue();
	}

	@Override
	public void addProduct(Product product) {
		this.getHibernateTemplate().save(product);
	}

	@Override
	public void deleteProductByPid(Integer pid) {
		Product product = findProductByPid(pid);
		this.getHibernateTemplate().delete(product);
	}

	@Override
	public void updateProduct(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
