package cn.maoaixi.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.maoaixi.product.dao.ProductDao;
import cn.maoaixi.product.vo.Product;
import cn.maoaixi.utils.PageBean;
@Transactional
public class ProductServiceImpl implements ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public List<?> selectHotProduct() {

		return productDao.selectHotProduct();
	}

	@Override
	public List<?> selectNewProduct() {

		return productDao.selectNewProduct();
	}

	@Override
	public PageBean<Product> findByCid(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 8;
		int totalPage = 0;

		pageBean.setPage(page);
		pageBean.setLimit(limit);

		Integer totalCount = productDao.findCountByCid(cid);
		pageBean.setTotalCount(totalCount);

		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int start = (page - 1) * limit;
		List<Product> list = productDao.findByPage(start,limit,cid);
		pageBean.setProductList(list);

		return pageBean;
	}

	@Override
	public PageBean<Product> findProductByCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 8;
		int totalPage = 0;
		
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		
		Integer totalCount = productDao.findCountByCsid(csid);
		
		pageBean.setTotalCount(totalCount);

		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		
		int start = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(start,limit,csid);
		pageBean.setProductList(list);

		return pageBean;
	}

	@Override
	public Product findProductByPid(Integer pid) {
			
		return productDao.findProductByPid(pid);
	}

	@Override
	public PageBean<Product> findAllProductByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		int limit = 12;
		int totalCount = productDao.getAllProductCount();
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		int start = (page - 1) * limit;
		List<Product> list = productDao.findAllProductByPage(start, limit);
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setProductList(list);
		return pageBean;
	}

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	@Override
	public void deleteProductByPid(Integer pid) {
		productDao.deleteProductByPid(pid);
		
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

}
