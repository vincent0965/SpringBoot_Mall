package com.vincentTsai.SpringBootMall.Controller;

import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.Service.ProductService;
import com.vincentTsai.SpringBootMall.modal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        // 4-14 Http狀態碼
        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        //新增商品
        Integer productId = productService.createProduct(productRequest);
        //透過參數將商品查詢回來
        Product product = productService.getProductById(productId);
        //將查詢到的商品回傳給前端並給予狀態碼(4-14)
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){

        //檢查product是否存在
        Product product = productService.getProductById(productId);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //修改商品數據
        productService.updateProduct(productId, productRequest);
        //將修改後的值回傳
        Product updatwProduct = productService.getProductById(productId);
        //給予回傳的值以及狀態碼
        return ResponseEntity.status(HttpStatus.OK).body(updatwProduct);
    }





}
