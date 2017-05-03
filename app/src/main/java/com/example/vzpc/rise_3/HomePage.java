package com.example.vzpc.rise_3;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomePage extends Fragment {

    private OnFragmentInteractionListener listener;
    SharedPreferences sharedPref = null;

    public static HomePage newInstance() {
        return new HomePage();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            // restore the text that you saved in from onPause()
            // also remember to write onPause()
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(formattedDate);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        name = sharedPref.getString("Username", "");

        TextView tv = (TextView) view.findViewById(R.id.banner);
        tv.setText("Good morning, " + name + "!");

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }

       
    }

    public interface OnFragmentInteractionListener {
    }

    private String getPreferences(String key) {
        return sharedPref.getString(key, null);
    }
}