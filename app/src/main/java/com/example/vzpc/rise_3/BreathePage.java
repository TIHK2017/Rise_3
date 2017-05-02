package com.example.vzpc.rise_3;



import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentTabHost;
import android.widget.ImageButton;
import android.widget.ImageView;


public class BreathePage extends Fragment{
    private FragmentTabHost mTabHost;
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

        imageNum = 0;

        exerciseView = (ImageView) view.findViewById(R.id.exerciseView);
        ImageButton nextButt = (ImageButton) view.findViewById(R.id.nextButt);
        ImageButton prevButt = (ImageButton) view.findViewById(R.id.prevButt);

        nextButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(v);
            }
        });

        prevButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(v);
            }
        });

        return view;

    }

    void handleClick(View v) {
        if (v.getId() == R.id.nextButt) {
            imageNum = (imageNum+1)%3;

        } else {
            imageNum = (imageNum+2)%3;
        }
        switch (imageNum) {
            case 2:
                exerciseView.setImageResource(R.drawable.weather);
                break;
            case 1:
                exerciseView.setImageResource(R.drawable.meditate);
                break;
            default:
                exerciseView.setImageResource(R.mipmap.ic_launcher);
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
        mTabHost = null;
    }

    public interface OnFragmentInteractionListener {
    }
}