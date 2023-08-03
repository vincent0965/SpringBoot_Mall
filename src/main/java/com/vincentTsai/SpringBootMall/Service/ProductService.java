package com.vincentTsai.SpringBootMall.Service;

import com.vincentTsai.SpringBootMall.modal.Product;

public interface ProductService {
    Product getProductById(Integer productId);
}
