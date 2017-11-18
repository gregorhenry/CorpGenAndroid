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
