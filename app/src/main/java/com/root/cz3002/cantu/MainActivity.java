package com.root.cz3002.cantu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hide upper bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        Bundle bundleOld = getIntent().getExtras();
        Intent intent = getIntent();
        String mode = "";
        if (intent.hasExtra("MODE")) {
            mode = bundleOld.getString("MODE");
        }
        if (intent.hasExtra("ID")) {
            id = bundleOld.getString("ID");
        }
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        if (mode.equals("canteen")) {
            View upperBar = inflater.inflate(R.layout.canteen_bar, (ViewGroup) findViewById(R.id.parent));
        }
        else {
            View upperBar = inflater.inflate(R.layout.cuisine_bar, (ViewGroup) findViewById(R.id.parent));
        }
    }
}
