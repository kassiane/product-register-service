package com.kassiane.four.all.product.register.dao.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kassiane.four.all.product.register.service.dao.ProductDAO;
import com.kassiane.four.all.product.register.service.domain.Product;

public class ProductDAOTest {

    private static ProductDAO productDAO;
    private static ApplicationContext context;
    private static File imageFile;
    private static Product withImage;
    private static Product withoutImage;

    @BeforeClass
    public static void setUp() throws IOException {
        context = new ClassPathXmlApplicationContext("spring-Database.xml");
        productDAO = (ProductDAO) context.getBean("productDAOImpl");
        productDAO.deleteAllProducts();
        imageFile = context.getResource("/images/defaultImage.png").getFile();
        final ImageIcon icon = new ImageIcon(ImageIO.read(imageFile));
        withImage = new Product(0, "test", 1.234567f, icon);
        withoutImage = new Product(0, "test", 1.234567f, null);

    }

    @Test(expected = SQLException.class)
    public void shouldReturnExceptionOnUpdateInexistentProduct() throws SQLException {
        final Product product = ProductDAOTest.withoutImage;
        productDAO.update(product);
    }

    @Test
    public void shouldInsertProductSuccesfullyWithoutImage() throws SQLException, IOException {

        productDAO.deleteAllProducts();
        final Product product = ProductDAOTest.withoutImage;
        final long id = productDAO.insert(product, null);

        final List<Product> products = productDAO.listProducts();
        Assert.assertEquals(1, products.size());

        final Product savedProduct = products.get(0);
        Assert.assertEquals(id, savedProduct.getId());
        Assert.assertEquals(product.getName(), savedProduct.getName());
        Assert.assertEquals(product.getPrice(), savedProduct.getPrice(), 0.0001);
        Assert.assertEquals(product.getIcon(), savedProduct.getIcon());
    }

    @Test
    public void shouldInsertProductSuccesfullyWithImage() throws SQLException, IOException {

        productDAO.deleteAllProducts();
        final Product product = ProductDAOTest.withImage;
        final long id = productDAO.insert(product, imageFile);

        final List<Product> products = productDAO.listProducts();
        Assert.assertEquals(1, products.size());

        final Product savedProduct = products.get(0);
        Assert.assertEquals(id, savedProduct.getId());
        Assert.assertEquals(product.getName(), savedProduct.getName());
        Assert.assertEquals(product.getPrice(), savedProduct.getPrice(), 0.0001);
        Assert.assertNotNull(savedProduct.getIcon());
    }

    @Test
    public void shouldReturnProductListSuccessfully() throws IOException, SQLException {
        productDAO.deleteAllProducts();

        final Product withoutImage = ProductDAOTest.withoutImage;
        final Product withImage = ProductDAOTest.withImage;
        productDAO.insert(withoutImage, null);
        productDAO.insert(withImage, imageFile);

        final List<Product> products = productDAO.listProducts();

        Assert.assertEquals(2, products.size());

    }
}
