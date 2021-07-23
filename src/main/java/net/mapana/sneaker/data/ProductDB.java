package net.mapana.sneaker.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.mapana.sneaker.api.dto.Product;

/**
 * @author guidocafiel
 */
public class ProductDB {

    private final String FILE_NAME = "productsbd.json";

    private List<Product> products = new LinkedList<>();
    private Gson gson = new Gson();
    private static final Type PRODUCTS_TYPE = new TypeToken<List<Product>>() {
    }.getType();

    private ProductDB() {

        File jsonFile = new File(FILE_NAME);
        if (jsonFile.exists()) {
            try {
                JsonReader reader = new JsonReader(new FileReader(jsonFile));
                this.products = gson.fromJson(reader, PRODUCTS_TYPE);
                if (products == null) {
                    products = new LinkedList<>();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProductDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List getProducts() {
        return this.products;
    }

    public void saveProduct(Product product) throws IOException {
        products.add(product);
        FileWriter fw = new FileWriter(FILE_NAME);
        fw.write(gson.toJson(products));
        fw.close();
    }

    public static ProductDB getInstance() {
        return ProductDBHolder.INSTANCE;

    }

    private static class ProductDBHolder {

        private static final ProductDB INSTANCE = new ProductDB();
    }
}
