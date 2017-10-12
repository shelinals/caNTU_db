package com.root.cz3002.cantu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by brigi on 10/10/2017.
 */

public class Credit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit);

        CreditItem cr = new CreditItem(10.00);

        TextView tv1 = (TextView) findViewById(R.id.crText);
        tv1.setText("Credit:");
        tv1.setTextSize(20);

        TextView tv2 = (TextView) findViewById(R.id.credit);
        tv1.setText(String.valueOf(cr.getCredit()));
        tv1.setTextSize(20);
    }
}
