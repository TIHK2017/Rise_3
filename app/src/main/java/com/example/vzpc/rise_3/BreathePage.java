package com.example.vzpc.rise_3;



import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


public class BreathePage extends Fragment{
    private OnFragmentInteractionListener listener;
    private int imageNum;
    private  ImageView exerciseView;

    public static BreathePage newInstance() {
        return new BreathePage();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breathe_page, container, false);

        // number of images to cycle through
        imageNum = 0;

        //
        exerciseView = (ImageView) view.findViewById(R.id.exerciseView);
        ImageButton nextButt = (ImageButton) view.findViewById(R.id.nextButt);
        ImageButton prevButt = (ImageButton) view.findViewById(R.id.prevButt);

        // ActionListener for the buttons
        nextButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(v); // see comments for this function below
            }
        });

        prevButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(v); // same as above
            }
        });

        return view;

    }

    // handles what to do to the ImageView once a button is pressed
    // '%' is the modulo operator. it does remainder division
    // 2%3 = 2 because 2/3 = 0 (remainder 2)
    // 0%3 = 0, and most interestingly,
    // 3%3 = 0 (because 3/3 = 1 (remainder 0))
    // mod is used for cycling thru a list of number from 0 to n
    // here it's used to cycle thru 0, 1, and 2
    void handleClick(View v) {
        if (v.getId() == R.id.nextButt) {
            imageNum = (imageNum+1)%3;

        } else {
            imageNum = (imageNum+2)%3;
        }
        // change pictures here
        switch (imageNum) {
            case 2:
                exerciseView.setImageResource(R.drawable.ha_breathing2);
                break;
            case 1:
                exerciseView.setImageResource(R.drawable.sitingup2);
                break;
            default: // default is all other numbers. default handles because imageNum starts at 0
                exerciseView.setImageResource(R.drawable.lying_down2);
                break;

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public interface OnFragmentInteractionListener {
    }
}

//References
//Exercise information adapted from:
//https://www.nytimes.com/2016/11/09/well/mind/breathe-exhale-repeat-the-benefits-of-controlled-breathing.html?_r=0
