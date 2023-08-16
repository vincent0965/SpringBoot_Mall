package com.vincentTsai.SpringBootMall.Controller;

import com.vincentTsai.SpringBootMall.DAO.ProductQueryParms;
import com.vincentTsai.SpringBootMall.DTO.ProductRequest;
import com.vincentTsai.SpringBootMall.Service.ProductService;
import com.vincentTsai.SpringBootMall.constant.ProductCategory;
import com.vincentTsai.SpringBootMall.modal.Product;
import com.vincentTsai.SpringBootMall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated //4-13
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //查詢商品列表
    //加入RequestParam => 新增參數 可透過輸入參數篩選出需要的資料
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getProducts(
//            //required = false => 讓參數不一定是必填
//            @RequestParam(required = false) ProductCategory category,
//            @RequestParam(required = false) String search
//    ){
//        //回傳一個List
//        List<Product> productList= productService.getProducts(category, search);
//        //回傳狀態碼以及List
//        return ResponseEntity.status(HttpStatus.OK).body(productList);
//    }


    // 透過class將多個參數整理後一併傳到後端
    // 回傳JSON格式(page) 讓前端知道總商品數以及結果
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //排序條件 Sorting (通常會給定預設值排序)
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
            //分頁 Pagination (為了保護資料庫的效能 限制搜尋的數量) 4-13(max、min、validated)
            @RequestParam(defaultValue = "3") @Max(1000) @Min(0) Integer limit, //選取幾筆資料
            @RequestParam(defaultValue = "0") @Min(0) Integer offset //跳過幾筆資料
    ){
        //如果查詢條件比較多 可以建立成class再一併傳送過去
        ProductQueryParms productQueryParms = new ProductQueryParms();
        productQueryParms.setCategory(category);
        productQueryParms.setSearch(search);
        productQueryParms.setOrderBy(orderBy);
        productQueryParms.setSort(sort);
        productQueryParms.setLimit(limit);
        productQueryParms.setOffset(offset);

        //DAO查詢回傳結果
        List<Product> productList= productService.getProducts(productQueryParms);

        //查詢總筆數(可以根據Category調整查詢總數)
        Integer total = productService.countProduct(productQueryParms);

        //使用page來裝分頁資料數、跳過數、總數、結果
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);

        //將結果回傳前端
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProductById(productId);

        // 檢查商品是否為Null 4-14 Http狀態碼
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

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }





}
