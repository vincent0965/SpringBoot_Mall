package com.vincentTsai.SpringBootMall.DAO.impl;

import com.vincentTsai.SpringBootMall.DAO.ProductDao;
import com.vincentTsai.SpringBootMall.DAO.ProductQueryParms;
import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.constant.ProductCategory;
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
    public List<Product> getProducts(ProductQueryParms productQueryParms) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description," +
                "created_date, last_modified_date " +
                "from product where 1 = 1";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        if(productQueryParms.getCategory() != null){
            sql = sql + " and category = :category";
            //category.name() => 因為category是enum類型 要使用name()轉成字串型態才可以加入map
            map.put("category", productQueryParms.getCategory().name());
        }

        if(productQueryParms.getSearch() != null){
            sql = sql + " and product_name like :search";
            map.put("search", "%"+productQueryParms.getSearch()+"%");
        }

        // 排序(因為有傳入預設值 因此不用檢查是否為Null)
        sql = sql + " order by " + productQueryParms.getOrderBy() + " " + productQueryParms.getSort();

        // 分頁
        sql = sql + " limit :limit offset :offset";
        map.put("limit", productQueryParms.getLimit());
        map.put("offset", productQueryParms.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

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

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "update product set product_name = :productName, category = :category," +
                "image_url = :imageUrl, price = :price, stock = :stock, description = :description, " +
                "last_modified_date = :lastModifiedDate where product_id = :productId";

        //將前端的資料塞入SQL中
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "delete from product where product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }


}
