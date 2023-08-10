package com.vincentTsai.SpringBootMall.Service.impl;

import com.vincentTsai.SpringBootMall.DAO.ProductDao;
import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.Service.ProductService;
import com.vincentTsai.SpringBootMall.modal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }


}
