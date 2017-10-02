package com.root.cz3002.cantu;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog loading;
    private EditText usernameText;
    private EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hide upper bars
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        getSupportActionBar().hide();

        final Context context = this;

        loading = new ProgressDialog(this);
        loading.setMessage("Logging in...");
        loading.setIndeterminate(true);
        loading.setCanceledOnTouchOutside(false);

        final Bundle bundle = new Bundle();
        usernameText = (EditText) findViewById(R.id.username);
        passwordText = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login_in);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentUser = new Intent(context, LauncherActivity.class);
                Intent intentOwner = new Intent(context, LauncherOwnerActivity.class);
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                //to check username and password against database
                //fill in checkDatabase's method -> enable logging in progress animation
                //get role (user, owner, none)
                if (username.equals("root") == true && password.equals("root") == true){ //dummy user check
                //if userid exists && (role.equals("user"){
                    String role = "user";
                    bundle.putString("ID", username);
                    bundle.putString("ROLE",role);
                    intentUser.putExtras(bundle);
                    Intent oldLauncher = new Intent("finish_activity");
                    sendBroadcast(oldLauncher);
                    startActivity(intentUser);
                    finish();
                }
                else if (username.equals("chris") == true && password.equals("chris") == true){ //dummy stall owner check
                //if userid exists && (role.equals("owner"){
                    String role = "owner";
                    bundle.putString("ID", username);
                    bundle.putString("ROLE",role);
                    intentOwner.putExtras(bundle);
                    Intent oldLauncher = new Intent("finish_activity");
                    sendBroadcast(oldLauncher);
                    startActivity(intentOwner);
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Wrong username or password!",
                            Toast.LENGTH_LONG).show();
            }
        });

    }
    private class checkDatabase extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("Prab","ANjeng");
           //check username, password, role in database
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loading.hide();
        }
    }
}
