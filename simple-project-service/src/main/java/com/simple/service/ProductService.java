package com.simple.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.simple.common.util.ImageHandleUtil;
import com.simple.dao.ProductDao;
import com.simple.model.PageResult;
import com.simple.model.Product;
import com.simple.model.ProductImage;
@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public Product getById(int id,boolean needFirstImage){
		Product p = productDao.getById(id);
		if (!needFirstImage) {
			p.setFirstImg(null);
		}
		return p;
	}
	
	public List<ProductImage> getImage(int productId) {
		return productDao.getImage(productId);
	}
	
	public PageResult query(String name,String phone,int productStatus ,int pageIndex,int pageSize){
		List<Product> products = queryList(name, phone, productStatus, pageIndex, pageSize);
		int total = queryCount(name, phone, productStatus);
		PageResult p = new PageResult(total, pageSize, pageIndex, products);
		return p;
	}
	public List<Product> queryList(String name,String phone,int productStatus ,int pageIndex,int pageSize) {
		return productDao.getList(phone, name,productStatus, pageIndex, pageSize);
	}
	
	public int queryCount(String name,String phone,int productStatus) {
		return productDao.getCount(phone, name, productStatus);
	}
	
	
	public PageResult query(String name,List<String> owners,int productStatus ,int pageIndex,int pageSize,boolean checkStock){
		List<Product> products = queryList(name, owners, productStatus, pageIndex, pageSize,checkStock);
		int total = queryCount(name, owners, productStatus);
		PageResult p = new PageResult(total, pageSize, pageIndex, products);
		return p;
	}
	
	public List<Product> queryList(String name,List<String> owners,int productStatus ,int pageIndex,int pageSize,boolean checkStock) {
		return productDao.getByOwners(owners, name,productStatus, pageIndex, pageSize,checkStock);
	}
	
	public int queryCount(String name,List<String> owners,int productStatus) {
		return productDao.getCountByOwners(owners, name, productStatus);
	}
	
	public List<String> queryProductOwners(int pageIndex,int pageSize) {
		return productDao.queryProductOwners(pageIndex,pageSize);
	}

	public void insert(Product product,String images) {
		productDao.addProduct(product);
		addProductImage(product,images);
	}
	
	public void update(Product product,String images){
		productDao.updateProduct(product);
		productDao.deleteProductImage(product.getId());
		addProductImage(product,images);
	}
	
	private void addProductImage(Product product,String images) {
		if (null != images) {
			List<String> imagelist = JSONArray.parseArray(images, String.class);
			//String[] ims = images.split(",");
			if ( null != imagelist && imagelist.size() > 0 ) {
				for (int i = 0 ; i < imagelist.size() ; i ++ ) {
					if (!StringUtils.isEmpty(imagelist.get(i))) {
						ProductImage pi = new ProductImage();
						pi.setImage(imagelist.get(i));
						pi.setProductId(product.getId());
						productDao.addProductImage(pi);
					}
				}
			}
		}
	}
	
	public int reduceStock(int productId,int count) {
		return productDao.reduceStock(productId,count);
	}
	
	public void updateProductStatus(int id,int status ) {
		productDao.updateStatus(id, status);
	}
	
	public int queryProductCount(int productStatus,String owner) {
		List<String> owners = new ArrayList<String>();
		owners.add(owner);
		return productDao.getCountByOwners(owners, null, productStatus);
	}
	
	public int queryNoStockCount(String owner) {
		return productDao.queryNoStockCount(owner);
	}
}
