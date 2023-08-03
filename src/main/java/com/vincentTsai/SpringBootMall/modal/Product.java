package com.vincentTsai.SpringBootMall.modal;

import com.vincentTsai.SpringBootMall.constant.ProductCategory;
import lombok.Data;

import java.util.Date;

@Data
public class Product {

    private Integer productId;
    private String productName;
    private ProductCategory category; //建立enum類型(要檢查rowmapper是否需要調整)
    private String imageUrl;
    private Integer price;
    private Integer stock;
    private String description;
    private Date createdDate;
    private Date lastModifiedDate;
}
