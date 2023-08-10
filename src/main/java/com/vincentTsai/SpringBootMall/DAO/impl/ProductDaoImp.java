package com.vincentTsai.SpringBootMall.DAO.impl;

import com.vincentTsai.SpringBootMall.DAO.ProductDao;
import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.modal.Product;
import com.vincentTsai.SpringBootMall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImp implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, " +
                "created_date, last_modified_date from mall.product where product_id = :productId;";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        if(productList.size() > 0){
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "insert into product(product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) " +
                "values (:productName, :category, :imageUrl, :price, :stock, " +
                ":description, :createdDate, :lastModifiedDate);";

        //將前端傳過來的參數塞進SQL中
        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        //將商品修改時間放入兩個變數中
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        //儲存資料庫自動生成的Id
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;


    }


}
