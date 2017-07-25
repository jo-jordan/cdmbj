package cn.maoaixi.product.service;

import java.util.List;

import cn.maoaixi.product.vo.Product;
import cn.maoaixi.utils.PageBean;

public interface ProductService {
	public List<?> selectHotProduct();

	public List<?> selectNewProduct();

	public PageBean<?> findByCid(Integer cid, Integer page);

	public PageBean<Product> findProductByCsid(Integer csid, Integer page);

	public Product findProductByPid(Integer pid);
	
	public PageBean<Product> findAllProductByPage(Integer page);

	public void addProduct(Product product);

	public void deleteProductByPid(Integer pid);

	public void updateProduct(Product product);
}
