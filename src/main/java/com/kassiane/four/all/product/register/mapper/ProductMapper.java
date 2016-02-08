package com.kassiane.four.all.product.register.mapper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import org.springframework.jdbc.core.RowMapper;

import com.kassiane.four.all.product.register.domain.Product;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        final int id = rs.getInt("id");
        final String name = rs.getString("name");
        final float price = rs.getFloat("price");
        final Blob blob = rs.getBlob("image");
        ImageIcon imageIcon = null;

        if (blob != null) {
            final int blobLength = (int) blob.length();
            final byte[] blobAsBytes = blob.getBytes(1, blobLength);
            imageIcon = new ImageIcon(blobAsBytes);
        }

        return new Product(id, name, price, imageIcon);
    }
}
