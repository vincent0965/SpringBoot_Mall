package com.vincentTsai.SpringBootMall.Service.impl;

import com.vincentTsai.SpringBootMall.DAO.ProductDao;
import com.vincentTsai.SpringBootMall.DAO.ProductQueryParms;
import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.Service.ProductService;
import com.vincentTsai.SpringBootMall.constant.ProductCategory;
import com.vincentTsai.SpringBootMall.modal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProducts(ProductQueryParms productQueryParms) {
        return productDao.getProducts(productQueryParms);
    }

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productDao.updateProduct(productId, productRequest);
    }

    @Override
    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }

    @Override
    public Integer countProduct(ProductQueryParms productQueryParms) {
        return productDao.countProduct(productQueryParms);
    }


}
