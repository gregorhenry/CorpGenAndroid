package pe.edu.utp.bottomnavigationsample.models;

import android.os.Bundle;

/**
 * Created by GrupoUTP on 23/09/2017.
 */

public class Product {
    private String name;
    private String description;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("description", description);
        return bundle;
    }

    public static Product from(Bundle bundle) {
        Product product = new Product();
        return product
                .setName(bundle.getString("name"))
                .setDescription(bundle.getString("description"));
    }
}
