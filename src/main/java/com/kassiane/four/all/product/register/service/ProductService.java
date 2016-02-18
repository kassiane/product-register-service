package com.kassiane.four.all.product.register.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kassiane.four.all.product.register.checker.ProductChecker;
import com.kassiane.four.all.product.register.service.dao.ProductDAO;
import com.kassiane.four.all.product.register.service.domain.Product;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(final ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product getProductById(final long id) {
        return this.productDAO.getProductById(id);
    }

    public List<Product> listProducts() {
        return this.productDAO.listProducts();
    }

    public long addProduct(final Product product, final File imageFile) throws SQLDataException, IOException {
        final ProductChecker productChecker = new ProductChecker(product);
        try {
            productChecker.checkProduct();
            return this.productDAO.insert(product, imageFile);
        } catch (final SQLException e) {
            throw new SQLDataException("Não foi possível salvar os dados.", e);
        } catch (final IOException e) {
            throw new IOException("Houve um problema com a imagem do produto.", e);
        } catch (final IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

    }

    public void updateProduct(final Product product) throws SQLException {
        final ProductChecker productChecker = new ProductChecker(product);
        productChecker.checkProduct();
        this.productDAO.update(product);
    }

}
