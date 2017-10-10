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
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String id;
    private String mode;
    private LinearLayout list;
    private boolean inStall = false;
    private View belongsToCanteenView;
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
        FloatingActionButton orderList = (FloatingActionButton) findViewById(R.id.orderList);
        orderList.setImageResource(R.drawable.order_list);
        orderList.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton dabaoer = (FloatingActionButton) findViewById(R.id.dabaoer);
        dabaoer.setImageResource(R.drawable.dabaoer);
        dabaoer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        dabaoer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DabaoerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        FloatingActionButton creditCard = (FloatingActionButton) findViewById(R.id.cash);
        creditCard.setImageResource(R.drawable.cash);
        creditCard.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pumpkin)));
        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private boolean addNewItemInList(LinearLayout list, final Stall cur, final MenuItem menu) {
        LinearLayout a = new LinearLayout(this);
        a.setOrientation(LinearLayout.HORIZONTAL);

        if(cur != null & menu==null) {
            ListView view = new ListView(this, cur);
            view.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
            if (mode.equals("canteen")) {
                view.setBackgroundResource(R.drawable.background_border_purple);
            } else {
                view.setBackgroundResource(R.drawable.background_border_orange);
            }
            a.addView(view);
            a.setTag(cur);
            a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    populateMenu(v);
                }
            });
        }
        else if(cur==null & menu != null){
            MenuListView menuView = new MenuListView(this, menu, mode);
            menuView.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT));
            if (mode.equals("canteen")) {
                menuView.setBackgroundResource(R.drawable.background_border_purple);
            } else {
                menuView.setBackgroundResource(R.drawable.background_border_orange);
            }
            a.setTag(menu);
            a.addView(menuView);

//            ViewGroup root = (ViewGroup) list.getParent().getParent();
//            RelativeLayout stallInfo = (RelativeLayout) findViewById(R.id.stall_info);
//            root.addView(stallInfo);
        }

        list.addView(a);
        return false;
    }

    public void populateList(View v) {
        list.removeAllViews();
        String category = v.getTag().toString();
        Toast.makeText(MainActivity.this, category, Toast.LENGTH_LONG).show();

        //dummy, example for populating list
        Stall temp = new Stall(1,"MiniWok","A","Chinese","10:00-20:00");
        addNewItemInList(list, temp, null);

        //populate groupStalls arraylist
        //using Database's method
        /*Stall temp;
       while (true) {
            temp = new Stall(get data from db);
            addNewItemInList(list, temp);
            if database kosong
                break;
        }
        for (Stall cur : gropStalls){

        }
      */
        belongsToCanteenView = v;
    }

    public void populateMenu(View v) {
        list.removeAllViews();
        Stall stall = (Stall) v.getTag();
        String stallName = stall.getName();
        System.out.println(stallName);
        Toast.makeText(MainActivity.this, stallName, Toast.LENGTH_LONG).show();

        //dummy, example for populating Menu Items
        MenuItem item = new MenuItem(1,"Sambal Fried Chicken","MiniWok",3.50);
        addNewItemInList(list, null, item);

        MenuItem item2 = new MenuItem(2,"Kung Pao Chicken Rice","MiniWok",4.00);
        addNewItemInList(list, null, item2);

        inStall = true;

        //populate MenuItems arraylist
        //using Database's method
       /* for (MenuItem item : MenuItems) {
            addNewItemInList(list, item);
        }*/
    }

    @Override
    public void onBackPressed()
    {
        if(inStall){
            System.out.println("balik ke canteen anjeng");
            populateList(belongsToCanteenView);
            inStall = false;
            return;
        }

        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs

    }
}
