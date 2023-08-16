package com.vincentTsai.SpringBootMall.DAO;

import com.vincentTsai.SpringBootMall.constant.ProductCategory;

//改善參數傳遞(將多個參數先整合成一個class再一併傳遞過去)
public class ProductQueryParms {
    private ProductCategory category;
    private String search;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
