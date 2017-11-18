package pe.edu.utp.corpgenprojectburningencounters.models;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.edu.utp.corpgenprojectburningencounters.R;

/**
 * Created by luis on 07/11/2017.
 */

public class BuyDrinks {
    private String name;
    private String description;
    private double price;


    public BuyDrinks(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price=price;
    }

    public BuyDrinks() {
    }

    public String getName() {
        return name;
    }

    public BuyDrinks setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BuyDrinks setDescription(String address) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public BuyDrinks setPrice(double price) {
        this.price = price;
        return this;
    }
    private Button puls, less;
    private TextView acountantTextView;

    private int acountant = 0;

    protected void onCreate(Bundle savedInstanceState) {

        acountantTextView=(TextView) acountantTextView.findViewById(R.id.acountantTextView);
        puls = (Button) puls.findViewById(R.id.buttonLess);

        puls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acountant++;

                acountantTextView.setText("Acountant: " + acountant);
            }
        });

        less = (Button) less.findViewById(R.id.buttonLess);

        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acountant--;

                acountantTextView.setText("Acountant: " + acountant);
            }
        });


    }
}
