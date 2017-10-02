package com.root.cz3002.cantu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LauncherOwnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_owner);

        //Hide upper bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        Bundle bundleOld = getIntent().getExtras();

        Button login = (Button) findViewById(R.id.login);
        Intent intent = getIntent();
        if (intent.hasExtra("ID")) {
            String id = bundleOld.getString("ID");
        }

        final Context context = this;
        final Bundle bundle = new Bundle();
        View orders = (View) findViewById(R.id.orders);
        orders.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent intent = new Intent(context, MainActivity.class);
                bundle.putString("MODE", "canteen");
                intent.putExtras(bundle);
                startActivity(intent);*/
                //start Orders activity
            }
        });


    }
}
