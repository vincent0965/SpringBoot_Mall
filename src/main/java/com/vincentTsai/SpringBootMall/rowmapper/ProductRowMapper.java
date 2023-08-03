package com.vincentTsai.SpringBootMall.rowmapper;

import com.vincentTsai.SpringBootMall.constant.ProductCategory;
import com.vincentTsai.SpringBootMall.modal.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));

        //String <=> enum
        String categoryStr = rs.getString("category"); //資料庫中取出來的值
        ProductCategory category = ProductCategory.valueOf(categoryStr); //轉換成enum類型
        product.setCategory(category); //將轉換成enum的值放入

        product.setImageUrl(rs.getString("image_url"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setCreatedDate(rs.getTimestamp("created_date"));
        product.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return product;
    }
}
