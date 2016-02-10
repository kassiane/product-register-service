package com.kassiane.four.all.product.register.service.dao.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.jdbc.core.JdbcTemplate;

import com.kassiane.four.all.product.register.service.dao.ProductDAO;
import com.kassiane.four.all.product.register.service.domain.Product;
import com.kassiane.four.all.product.register.service.mapper.ProductMapper;

public class ProductDAOImpl implements ProductDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(final DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	}

	@Override
	public List<Product> listProducts() {
		final String query = "select * from Product;";
		final List<Product> product = this.jdbcTemplateObject.query(query,
				new ProductMapper());
		return product;
	}

	@Override
	public Product getProductByName(final String name) {
		final String query = "select * from Product where name = ?";
		final Product product = this.jdbcTemplateObject.queryForObject(query,
				new Object[] { name }, new ProductMapper());
		return product;
	}

	@Override
	public Product getProductById(final long id) {
		final String query = "select * from Product where id = ?";
		final Product product = this.jdbcTemplateObject.queryForObject(query,
				new Object[] { id }, new ProductMapper());
		return product;
	}

	@Override
	public void update(final Product product) {
		final String query = "update Product set name = ?, price = ? where id = ?";
		this.jdbcTemplateObject.update(query, product.getName(),
				product.getPrice(), product.getId());

	}

	@Override
	public long insert(final Product product, final File imageFile)
			throws SQLException, IOException {
		final String name = product.getName();
		final float price = product.getPrice();

		final String query = "insert into Product (name, price, image) values (?, ?, ?)";
		final PreparedStatement statement = this.dataSource.getConnection()
				.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

		statement.setString(1, name);
		statement.setBigDecimal(2, BigDecimal.valueOf(price));
		if (imageFile != null) {
			final Path path = Paths.get(imageFile.getAbsolutePath());
			final byte[] bytes = Files.readAllBytes(path);
			final Blob imageBlob = new SerialBlob(bytes);
			statement.setBlob(3, imageBlob);
		} else
			statement.setNull(3, Types.NULL);

		statement.execute();

		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				return generatedKeys.getLong(1);
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}

	}

	@Override
	public void delete(final Product product) {
		final String query = "delete from Product where id = ?";
		this.jdbcTemplateObject.update(query, product.getId());

	}
}
