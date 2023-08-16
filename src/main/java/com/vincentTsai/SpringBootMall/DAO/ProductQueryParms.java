package com.vincentTsai.SpringBootMall.DAO;

import com.vincentTsai.SpringBootMall.constant.ProductCategory;

//改善參數傳遞(將多個參數先整合成一個class再一併傳遞過去)
public class ProductQueryParms {
    private ProductCategory category;
    private String search;

    private String orderBy;

    private String sort;

    private Integer limit;

    private Integer offset;

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
