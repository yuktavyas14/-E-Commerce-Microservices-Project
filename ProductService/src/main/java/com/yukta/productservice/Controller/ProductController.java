package com.yukta.productservice.Controller;


import com.yukta.productservice.Entity.Product;
import com.yukta.productservice.Service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> productList= productService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
       Product productList= productService.addProducts(product);
        return ResponseEntity.ok(productList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id){
        Product productList= productService.updateProducts(id,product);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable Long id){
         productService.deleteByid(id);

    }

}
