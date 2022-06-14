package com.example.instagwam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginScreenActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etpPassword;
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }
        etUsername = findViewById(R.id.etUsername);
        etpPassword = findViewById(R.id.etpPassword);
        bLogin = findViewById(R.id.bLogin);

        bLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Login button");
                String username = etUsername.getText().toString();
                String password = etpPassword.getText().toString();
                loginUser(username, password);
            }

            private void loginUser(String username, String password) {
                Log.i(TAG, "Attempting to login user " + username);
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null){
                            Log.e(TAG, "Issue with login", e);
                            Toast.makeText(LoginScreenActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        goMainActivity();
                        Toast.makeText(LoginScreenActivity.this, "Successful Login", Toast.LENGTH_SHORT ).show();
                    }
                });
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(LoginScreenActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}