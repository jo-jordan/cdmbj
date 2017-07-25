package cn.maoaixi.product.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.maoaixi.categorysecond.service.CategorySecondService;
import cn.maoaixi.categorysecond.vo.CategorySecond;
import cn.maoaixi.product.service.ProductService;
import cn.maoaixi.product.vo.Product;
import cn.maoaixi.utils.PageBean;

/**
 * 这个action要完成的是,从数据库中获取到属于二级分类的所有商品,压入栈中
 * 
 * @author lzjlxebr
 *
 */
@SuppressWarnings("serial")
public class ProductAction extends ActionSupport implements ModelDriven<Product> {
	/**************** 自动压栈区 **************/
	// 存放商品
	// private Product product;
	private Product product = new Product();

	@Override
	public Product getModel() {
		return product;
	}

	public Product getProduct() {
		return product;
	}

	// 存放分类
	private List<CategorySecond> categorySecondList;

	public List<CategorySecond> getCategorySecondList() {
		return categorySecondList;
	}

	// 注入pageBean
	private PageBean<Product> pageBean;

	public PageBean<Product> getPageBean() {
		return pageBean;
	}

	/**************** 自动压栈区end **************/

	/**************** 注入service区 **************/
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	/**************** 注入service区end **************/

	/**************** 特殊字段提供区 **************/

	// 提供cid字段
	private Integer cid;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	// csid
	private Integer csid;

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	// 提供page字段
	private Integer page;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	// 文件上传的三个属性
	private File upload;// 上传文件对象
	@SuppressWarnings("unused")
	private String uploadContentType;// 上传文件MIME类型,为下载使用
	private String uploadFileName;// 上传文件名称

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**************** 特殊字段提供区end **************/

	/**************** action方法区 **************/
	// 根据cid查找所有该分类下的所有商品
	@SuppressWarnings("unchecked")
	public String findCSByCid() {
		categorySecondList = (List<CategorySecond>) categorySecondService.findCSById(cid);
		pageBean = (PageBean<Product>) productService.findByCid(cid, page);
		return "findCSByCidSuccess";
	}

	// 根据二级分类的id查询商品,并分页显示
	@SuppressWarnings("unchecked")
	public String findProductByCsid() {
		categorySecondList = (List<CategorySecond>) categorySecondService.findCSById(cid);
		pageBean = productService.findProductByCsid(csid, page);
		return "findProductByCsidSuccess";
	}

	// 根据商品ID查询商品详情
	public String findProductByPid() {
		// categorySecondList = (List<CategorySecond>)
		// categorySecondService.findCSById(cid);
		product = productService.findProductByPid(product.getPid());
		return "findProductByPidSuccess";
	}

	// 商品管理:分页显示所有商品
	public String manage() {
		if (page == null) {
			page = 1;
		}
		categorySecondList = categorySecondService.findAllCS();
		pageBean = productService.findAllProductByPage(page);
		return "manageSuccess";
	}

	// 商品管理:添加商品
	public String addProduct() throws IOException {
		// 文件上传操作:
		// 1,获取上传的路径
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String realPath = path + "/" + csid + "/" + uploadFileName;

		File diskFile = new File(realPath);
		// 2,获取上传文件,文件存到指定目录下
		FileUtils.copyFile(upload, diskFile);

		// 开始添加商品
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		product.setImage("/products/" + csid + "/" + uploadFileName);
		product.setPdate(new Date());

		productService.addProduct(product);

		return "addProductSuccess";
	}

	// 商品管理:删除商品
	public String deleteProductByPid() {
		// 先删除原来图片
		Product p = productService.findProductByPid(product.getPid());
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String imageDir = path.substring(0, path.lastIndexOf("/products"));
		System.out.println("********************************************************realPath:" + imageDir);
		String imagePath = imageDir + p.getImage();
		File image = new File(imagePath);
		if (image.exists()) {
			image.delete();
		}
		productService.deleteProductByPid(product.getPid());
		return "deleteProductByPidSuccess";
	}

	// 商品管理:修改商品
	public String modifyProductByPid() throws IOException {
		Integer pid = product.getPid();
		System.out.println(pid);
		// 修改图片
		product.setPid(pid);
		// 先删除原来图片
		Product p = productService.findProductByPid(pid);
		String path = ServletActionContext.getServletContext().getRealPath("/products");
		String imageDir = path.substring(0, path.lastIndexOf("/products"));
		System.out.println("********************************************************realPath:" + imageDir);
		String imagePath = imageDir + p.getImage();
		File image = new File(imagePath);
		if (image.exists()) {
			image.delete();
		}

		// 再添加新的图片
		String realPath = path + "/" + csid + "/" + uploadFileName;
		File destFile = new File(realPath);
		FileUtils.copyFile(upload, destFile);
		
		product.setPdate(new Date());
		product.setImage("/products/" + csid + "/" + uploadFileName);
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		
		productService.updateProduct(product);
		return "modifyProductByPidSuccess";
	}
	
	//	跳转到修改商品页面
	public String goModifyProduct(){
		categorySecondList = categorySecondService.findAllCS();
		product =  productService.findProductByPid(product.getPid());
		return "goModifyProductSuccess";
	}
	/**************** action方法区 **************/

}
