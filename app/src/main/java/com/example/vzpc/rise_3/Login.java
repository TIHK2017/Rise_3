package com.example.vzpc.rise_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    SharedPreferences sharedPref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        final EditText username = (EditText) findViewById(R.id.Username);
        final EditText password = (EditText) findViewById(R.id.Password);
        final Button button = (Button) findViewById(R.id.Login);

        // ActionListener for password field
        // Allows users to click on Login by pressing Enter
        EditText.OnEditorActionListener passwordListener = new EditText.OnEditorActionListener(){
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // put code to click on Login button
                    button.performClick();
                }
                return true;
            }
        };

        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) // creates what the onClick will do
            {
                String name = username.getText().toString();
                if (name.equals("V.Tam") && password.getText().toString().equals("admin"))
                {
                    Intent intent = new Intent(Login.this, RiseApp.class);
                    intent.putExtra("Username", name);
                    startActivity(intent);
                }
                //startActivity(new Intent(getApplicationContext(), HomePage.class));
                else
                    password.setError("Incorrect Username and/or Password");
            }
        };
        // adds Listeners
        password.setOnEditorActionListener(passwordListener);
        button.setOnClickListener(myListener);

    }

}
