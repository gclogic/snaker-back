package net.mapana.sneaker.api;

import net.mapana.sneaker.api.dto.Product;
import net.mapana.sneaker.business.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author guidocafiel
 */
@SpringBootTest
class ProductControllerTest {

    @Autowired
    ProductService productService;


    @Test
    void getProducts() {
        ProductController productController = new ProductController(productService);
        ResponseEntity response = productController.getProducts();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createProduct() {
        ProductController productController = new ProductController(productService);
        ResponseEntity response = productController.createProduct(null);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        Product product = new Product();
        response = productController.createProduct(product);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        product.setTitle("My test");
        response = productController.createProduct(product);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

        product.setImageUrl("myUrl");
        product.setTitle(null);
        response = productController.createProduct(product);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
}