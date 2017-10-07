package com.root.cz3002.cantu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String id;
    private String mode;
    private LinearLayout list;
    //private ArrayList<Stall> groupStalls = new ArrayList<Stall>(); //to fill with content of list for stalls in canteen/cuisine choice

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
        if (intent.hasExtra("MODE")) {
            mode = bundleOld.getString("MODE");
        }
        if (intent.hasExtra("ID")) {
            id = bundleOld.getString("ID");
        }

        final Context context = this;
        final Bundle bundle = new Bundle();

        FloatingActionButton switchMode = (FloatingActionButton) findViewById(R.id.switchMode);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        list = (LinearLayout) inflater.inflate(R.layout.list_view, (ViewGroup) findViewById(R.id.list));
        if (mode.equals("canteen")) {
            View upperBar = inflater.inflate(R.layout.canteen_bar, (ViewGroup) findViewById(R.id.toolbar));
            switchMode.setImageResource(R.drawable.cuisine);
            switchMode.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_orange_light)));
            switchMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    bundle.putString("ID",id);
                    bundle.putString("MODE", "cuisine");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
            populateList(findViewById(R.id.cana));
        }
        else {
            View upperBar = inflater.inflate(R.layout.cuisine_bar, (ViewGroup) findViewById(R.id.toolbar));
            switchMode.setImageResource(R.drawable.store);
            switchMode.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.holo_purple)));
            switchMode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    bundle.putString("ID",id);
                    bundle.putString("MODE", "canteen");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            });
            populateList(findViewById(R.id.chinese));
        }

    }
    private boolean addNewItemInList(LinearLayout list, final Stall cur) {
        LinearLayout a = new LinearLayout(this);
        a.setOrientation(LinearLayout.HORIZONTAL);
        ListView view = new ListView(this, cur);
        if (mode.equals("canteen")) {
            view.setBackgroundResource(R.drawable.background_border_purple);
        }
        else{
            view.setBackgroundResource(R.drawable.background_border_orange);
        }
        view.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
        a.addView(view);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, cur.getName(), Toast.LENGTH_LONG).show();
                //go to Menu list of selected stall
            }
        });
        list.addView(a);
        return false;
    }

    public void populateList(View v) {
        list.removeAllViews();
        String category = v.getTag().toString();
        Toast.makeText(MainActivity.this, category, Toast.LENGTH_LONG).show();

        //dummy, example for populating list
        Stall temp = new Stall(1,"MiniWok","A","Chinese","10:00-20:00");
        addNewItemInList(list, temp);

        //populate groupStalls arraylist
        //using Database's method
       /* for (Stall cur : groupStalls) {
            addNewItemInList(list, cur);
        }*/
    }
}
