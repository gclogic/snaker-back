package net.mapana.sneaker.api;

import net.mapana.sneaker.api.dto.Product;
import net.mapana.sneaker.business.ProductService;
import net.mapana.sneaker.commons.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author guidocafiel
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity getProducts() {
        return new ResponseEntity(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createProduct(@RequestBody Product product) {

        try {
            productService.createProduct(product);
        } catch (BusinessException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            return new ResponseEntity("Internal Exception code 0100", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("Product added successfully",HttpStatus.CREATED);
    }

}
