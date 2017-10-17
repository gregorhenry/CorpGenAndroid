package pe.edu.utp.bottomnavigationsample.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.edu.utp.bottomnavigationsample.R;
import pe.edu.utp.bottomnavigationsample.adapters.ProductsAdapter;
import pe.edu.utp.bottomnavigationsample.models.Product;
import pe.edu.utp.bottomnavigationsample.models.ProductsRepository;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {
    RecyclerView productsRecyclerView;
    ProductsAdapter productsAdapter;
    RecyclerView.LayoutManager productsLayoutManager;
    List<Product> products;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        productsRecyclerView = (RecyclerView)
                view.findViewById(R.id.productsRecyclerView);
        products = ProductsRepository.getProducts();
        productsAdapter = new ProductsAdapter(products);
        productsLayoutManager = new GridLayoutManager(view.getContext(), 2);
        productsRecyclerView.setAdapter(productsAdapter);
        productsRecyclerView.setLayoutManager(productsLayoutManager);
        adaptGridTo(getResources().getConfiguration());
        return view;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adaptGridTo(newConfig);
    }

    private void adaptGridTo(Configuration configuration) {
        int spanCount = configuration.orientation == ORIENTATION_PORTRAIT ? 2 : 3;
        ((GridLayoutManager)productsLayoutManager).setSpanCount(spanCount);
    }
}
