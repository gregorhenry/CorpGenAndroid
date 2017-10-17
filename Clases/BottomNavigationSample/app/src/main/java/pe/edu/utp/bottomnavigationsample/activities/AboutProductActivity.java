package pe.edu.utp.bottomnavigationsample.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import pe.edu.utp.bottomnavigationsample.R;
import pe.edu.utp.bottomnavigationsample.models.Product;

public class AboutProductActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView descriptionTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        Product product = Product.from(getIntent().getExtras());
        nameTextView.setText(product.getName());
        descriptionTextView.setText(product.getDescription());

    }

}
