package com.vincentTsai.SpringBootMall.Service;

import com.vincentTsai.SpringBootMall.DAO.ProductQueryParms;
import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.constant.ProductCategory;
import com.vincentTsai.SpringBootMall.modal.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParms productQueryParms);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
