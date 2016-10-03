package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Product;
import com.simple.model.ProductImage;

@Repository
@DatabaseTemplate("st_all")
public class ProductDao extends BaseIbatisDao{
	
	public Product getById(int id) {
		return this.sqlSession.selectOne("product.queryById",id);
	}

	public List<ProductImage> getImage(int productId) {
		return this.sqlSession.selectList("product.queryImage",productId);
	}
	
	public List<Product> getList(String phone,String name,int productStatus,int pageIndex,int pageSize) {
		if (pageIndex < 1) {
			pageIndex  =1;
		}
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("name", name);
		param.put("owner", phone);
		param.put("startnum", (pageIndex-1)*pageSize);
		param.put("pageSize", pageSize);
		param.put("productStatus", productStatus);
		return this.sqlSession.selectList("product.queryList",param);
	}
	
	public int getCount(String phone,String name,int productStatus) {
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("name", name);
		param.put("owner", phone);
		param.put("productStatus", productStatus);
		return this.sqlSession.selectOne("product.queryCount",param);
	}
	
	
	public List<Product> getByOwners(List<String> owners,String name,int productStatus,int pageIndex,int pageSize,boolean checkStock) {
		if (pageIndex < 1) {
			pageIndex  =1;
		}
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("name", name);
		param.put("ids", owners);
		param.put("startnum", (pageIndex-1)*pageSize);
		param.put("pageSize", pageSize);
		param.put("productStatus", productStatus);
		if (checkStock) {
			param.put("checkStock", 1);
		}else {
			param.put("checkStock", 0);
		}
		return this.sqlSession.selectList("product.queryByOwner",param);
	}
	
	public int getCountByOwners(List<String> owners,String name,int productStatus) {
		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("name", name);
		param.put("ids", owners);
		param.put("productStatus", productStatus);
		return this.sqlSession.selectOne("product.queryCountByOwner",param);
	}
	
	public int addProduct(Product product) {
		return this.sqlSession.insert("product.insert",product);
	}
	
	public int updateProduct(Product product) {
		return this.sqlSession.update("product.update",product);
	}
	
	public int reduceStock(int id,int count) {
		Map param = new HashMap();
		param.put("id", id);
		param.put("productCount", count);
		return this.sqlSession.update("product.reduceStock",param);
	}
	
	public int increaseStock(int id,int count) {
		Map param = new HashMap();
		param.put("id", id);
		param.put("productCount", count);
		return this.sqlSession.update("product.increaseStock",param);
	}
	
	public void addProductImage(ProductImage image) {
		this.sqlSession.insert("product.insertImage",image);
	}
	
	public void deleteProductImage(int productId) {
		this.sqlSession.delete("product.deleteImage",productId);
	}
	
	public void updateStatus(int id,int status) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", id);
		param.put("productStatus", status);
		this.sqlSession.update("product.updatestatus",param);
	}
	
	public int queryNoStockCount(String owner) {
		return this.sqlSession.selectOne("product.queryNoStock",owner);
	}
	
	public List<String> queryProductOwners(int pageIndex,int pageSize) {
		if (pageIndex <=0) {
			pageIndex = 1;
		}
		Map param = new HashMap();
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("end", pageSize);
		return this.sqlSession.selectList("product.queryOwners",param);
	}

}
