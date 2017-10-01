package pe.edu.utp.bottomnavigationsample.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrupoUTP on 23/09/2017.
 */

public class ProductsRepository {

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Sample Name 1", "Sample Description 1"));
        products.add(new Product("Sample Name 2", "Sample Description 2"));
        products.add(new Product("Sample Name 3", "Sample Description 3"));
        products.add(new Product("Sample Name 4", "Sample Description 4"));
        products.add(new Product("Sample Name 5", "Sample Description 5"));
        return products;
    }
}
