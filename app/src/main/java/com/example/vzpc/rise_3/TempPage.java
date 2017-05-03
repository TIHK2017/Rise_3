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
import android.widget.Toast;

public class TempPage extends Fragment {

    private OnFragmentInteractionListener listener;
    private EditText tempDesired;
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

        tempDesired = (EditText) view.findViewById(R.id.tempDesired);
        String value = tempDesired.getText().toString();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Temperature",value);

                //sharedPref.getString("Temperature", "0"
        setTemp = (Button) view.findViewById(R.id.setTemp);
        setTemp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if  (tempDesired.getText().toString().length()==0 ) {

                    tempDesired.setError("Input temperature here.");
                }
                else{
                    Toast.makeText(getContext(),"Temperature set",
                            Toast.LENGTH_SHORT).show();
                }
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