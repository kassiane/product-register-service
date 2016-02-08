package com.kassiane.four.all.product.register.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;

import com.kassiane.four.all.product.register.service.dao.ProductDAO;
import com.kassiane.four.all.product.register.service.domain.Product;

public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(final ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(final Product product, final File imageFile) throws DataAccessException, SQLException, IOException {
        // when everything is ok (checked), save in the database

        this.productDAO.insert(product, imageFile);

    }

    public void updateProduct(final Product product) {

    }

}
