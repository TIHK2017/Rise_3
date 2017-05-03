package com.example.vzpc.rise_3;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.lang.Math.abs;

public class TempPage extends Fragment {

    private OnFragmentInteractionListener listener;
    private EditText tempDesired;
    private TextView currView;
    private Button tempEnergy;
    SharedPreferences sharedPref = null;
    private Button setTemp;

    public static TempPage newInstance() {
        return new TempPage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_temp_page, container, false);

        // restore set temperature to tempDesired when the view is recreated
        tempDesired = (EditText) view.findViewById(R.id.tempDesired);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        tempDesired.setText(sharedPref.getString("Temperature",""));

        // set current temperature via sharedPreferences
        currView = (TextView) view.findViewById(R.id.current);
        final int curr_temp = sharedPref.getInt("Current Temp", -273);
        String curr_text = "Current: " + String.valueOf(curr_temp) + " °F";
        currView.setText(curr_text);

        // actionlisteners
        setTemp = (Button) view.findViewById(R.id.setTemp);
        setTemp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if  (tempDesired.getText().toString().length()==0 ) {

                    tempDesired.setError("Input temperature here.");
                }
                else if (Integer.parseInt(tempDesired.getText().toString())>= 100){
                    tempDesired.setError("Temp too high. Choose value below 100°F.");


                }
                else if (Integer.parseInt(tempDesired.getText().toString()) <= 40){
                    tempDesired.setError("Temp too low. Choose value above 40°F.");
                }
                else{
                    // store the temperature
                    sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Temperature", tempDesired.getText().toString());
                    editor.commit();
                    // confirmation
                    Toast.makeText(getContext(),"Temperature set",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        tempEnergy = (Button) view.findViewById(R.id.tempEnergy);
        tempEnergy.setOnClickListener(new View.OnClickListener()
        {
            // trying to calculate power consumption
            @Override
            public void onClick(View v) {
                sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                // constants
                double k = 0.563;   // power constant
                int time = 1;       // hours
                int diff;           // current/desired temp difference
                double power;       // power consumption. changes depending on the temperature
                                    // differential
                String message;

                tempDesired = (EditText) getView().findViewById(R.id.tempDesired);

                int temp = Integer.parseInt(tempDesired.getText().toString());
                int curr = sharedPref.getInt("Current Temp", 0);

                // calculate values
                diff = abs(temp - curr);    // assumes heating takes as much energy as cooling does
                power = k*time*diff;        // self-made formula. replace with something more
                                            // accurate to system
                message = "Power consumption after " + String.valueOf(time) +
                         " hours is " + String.valueOf(power)+"kW";

                // confirmation
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            listener = (OnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}