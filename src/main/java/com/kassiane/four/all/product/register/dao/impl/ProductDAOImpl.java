package com.kassiane.four.all.product.register.dao.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kassiane.four.all.product.register.dao.ProductDAO;
import com.kassiane.four.all.product.register.domain.Product;
import com.kassiane.four.all.product.register.mapper.ProductMapper;

public class ProductDAOImpl implements ProductDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
	     this.dataSource = dataSource;
	     this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	}
	
	@Override
	public List<Product> listProducts() {
		String query = "select * from Product;" ;
		List <Product> product = jdbcTemplateObject.query(query, 
									new ProductMapper());
	      return product;
	}

	@Override
	public Product getProductByName(String name) {
		String query = "select * from Product where name = ?" ;
		Product product = jdbcTemplateObject.queryForObject(query, 
                new Object[]{name}, new ProductMapper());
	      return product;
	}

	@Override
	public Product getProductById(int id) {
		String query = "select * from Product where id = ?" ;
		Product product = jdbcTemplateObject.queryForObject(query, 
                new Object[]{id}, new ProductMapper());
	      return product;
	}

	@Override
	public void update(Product product) {
		String query = "update Product set"
				+ "name = ?"
				+ "price = ?"
				+ "image = ?"
				+ "where id = ?" ;
		jdbcTemplateObject.update(query, product.getName(), product.getPrice(), product.getIcon());
		
	}
	
	@Override
	public void insert(Product product, File imageFile) throws SQLException, DataAccessException, IOException {
		String name = product.getName();
		float price = product.getPrice();
		
		String query = "insert into Product (name, price, image) "
				+ "values (?, ?, ?)" ;
		PreparedStatement pstmt = 
				dataSource.getConnection().prepareStatement(query); 
		
		pstmt.setString(1, name);
		pstmt.setBigDecimal(2, BigDecimal.valueOf(price));
		if(imageFile != null){
			Path path = Paths.get(imageFile.getAbsolutePath());
			byte[] bytes = Files.readAllBytes(path);
			Blob imageBlob = new SerialBlob(bytes);
			pstmt.setBlob(3, imageBlob);
		}
		else
			pstmt.setNull(3, Types.NULL);

		pstmt.execute();

	}
	
	@Override
	public void delete(Product product) {
		String query = "delete from Product where id = ?" ;
		jdbcTemplateObject.update(query, product.getId());
		
	}
}
