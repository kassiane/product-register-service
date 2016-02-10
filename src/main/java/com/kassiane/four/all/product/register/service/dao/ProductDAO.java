package com.kassiane.four.all.product.register.service.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.kassiane.four.all.product.register.service.domain.Product;

public interface ProductDAO {

    public List<Product> listProducts();

    public Product getProductById(long id);

    public void update(Product product) throws SQLException;

    public long insert(Product product, File imageFile) throws SQLException, IOException;

    public void delete(Product product);

    public void deleteAllProducts();
}
