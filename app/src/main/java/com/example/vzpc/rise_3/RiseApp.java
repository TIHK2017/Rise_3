// this test will create bottom navigation view and fragments


package com.example.vzpc.rise_3;


import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class RiseApp extends AppCompatActivity implements
        HomePage.OnFragmentInteractionListener,
        BreathePage.OnFragmentInteractionListener,
        BlindsPage.OnFragmentInteractionListener,
        TempPage.OnFragmentInteractionListener

{
    SharedPreferences sharedPref = null;
    //data that we are saving
    int current_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rise_app);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        current_temp = 80;
        savePreferences("Current Temp", current_temp);

        // saves username to Shared Preferences
        String name = getIntent().getExtras().getString("Username");
        savePreferences("Username", name);

        // initializes HomePage as first fragment to display
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, HomePage.newInstance());
        fragmentTransaction.commit();

        // ActionListener for the BottomNavigationBar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment fragment = null;
                switch (item.getItemId())
                {
                    case R.id.menu_home:
                        fragment = HomePage.newInstance();
                        break;
                    case R.id.menu_breathe:
                        fragment = BreathePage.newInstance();
                        break;
                    case R.id.menu_blinds:
                        fragment = BlindsPage.newInstance();
                        break;
                    case R.id.menu_temp:
                        fragment = TempPage.newInstance();
                        break;
                }
                if (fragment != null)
                {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frameLayout, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }

    // saves data to the sharedPreferences file
    // key is a name you assign to the data you are saving that allows you to retrieve it
    private void savePreferences(String key, String value)
    {
        Editor edit = sharedPref.edit();
        edit.putString(key, value);
        edit.commit();
    }

    private void savePreferences(String key, int value)
    {
        Editor edit = sharedPref.edit();
        edit.putInt(key, value);
        edit.commit();
    }
}