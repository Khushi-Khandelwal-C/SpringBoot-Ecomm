package com.example.FirstProject.controller;

import com.example.FirstProject.model.Product;
import com.example.FirstProject.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController

@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;
    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity< >(service.getProducts(), HttpStatus.OK);
    }

    @RequestMapping("/product/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
            Product product1 = service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile) throws IOException {
        Product product1 = null;
        try {
            product1 = service.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(product1 != null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        else return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product!= null){
            service.deleteProduct(id);
            return new ResponseEntity<> ("Deleted",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
    }



}
