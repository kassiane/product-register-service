package com.kassiane.four.all.product.register.service.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.kassiane.four.all.product.register.service.domain.Product;

public interface ProductDAO {
	
	public List<Product> listProducts();
	public Product getProductByName(String name);
	public Product getProductById(int id);
	
	public void update(Product product);
	
	public void insert(Product product, File imageFile) throws SQLException, DataAccessException, IOException;
	
	public void delete(Product product);
}
