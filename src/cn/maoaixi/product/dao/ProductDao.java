package cn.maoaixi.product.dao;

import java.util.List;

import cn.maoaixi.product.vo.Product;

public interface ProductDao {
	public List<?> selectHotProduct();

	public List<?> selectNewProduct();

//	public PageBean<?> findByCid(Integer cid, Integer page);

	public Integer findCountByCid(Integer cid);

	public List<Product> findByPage(int start, int limit, Integer cid);

	public Integer findCountByCsid(Integer csid);

	public List<Product> findByPageCsid(int start, int limit, Integer csid);

	public Product findProductByPid(Integer pid);
	
	public List<Product> findAllProductByPage(int start,int limit);
	
	public Integer getAllProductCount();

	public void addProduct(Product product);

	public void deleteProductByPid(Integer pid);

	public void updateProduct(Product product);
}
