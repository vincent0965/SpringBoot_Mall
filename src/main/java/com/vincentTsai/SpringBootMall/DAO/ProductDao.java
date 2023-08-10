package com.vincentTsai.SpringBootMall.DAO;

import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.modal.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
