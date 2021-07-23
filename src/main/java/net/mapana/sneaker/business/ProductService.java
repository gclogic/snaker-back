package net.mapana.sneaker.business;

import net.mapana.sneaker.api.dto.Product;
import net.mapana.sneaker.commons.BusinessException;
import net.mapana.sneaker.data.ProductDB;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author guidocafiel
 */
@Service
public class ProductService {


    public void createProduct(Product product) throws BusinessException, IOException {

        if (product == null) {
            throw new BusinessException("The product's value must be different of null");
        }

        if(product.getTitle() == null || product.getTitle().trim().isEmpty()){
            throw new BusinessException("Title it's a required field, title is null or empty");
        }

        if(product.getImageUrl() == null || product.getImageUrl().trim().isEmpty()){
            throw new BusinessException("Image URL it's a required field, description is null or empty");
        }

        ProductDB.getInstance().saveProduct(product);
    }

    public List<Product> getAllProducts(){
        return ProductDB.getInstance().getProducts();
    }

}
