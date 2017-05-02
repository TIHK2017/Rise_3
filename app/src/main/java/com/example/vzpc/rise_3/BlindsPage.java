package com.example.vzpc.rise_3;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import static java.lang.Math.round;

public class BlindsPage extends Fragment {

    private OnFragmentInteractionListener listener;

    public static BlindsPage newInstance() {
        return new BlindsPage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blinds_page, container, false);

        SeekBar mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        final ImageView blindsView = (ImageView) view.findViewById(R.id.blindsView);


        SeekBar.OnSeekBarChangeListener mSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            // gets called when you move the slider bar
            // here we change the height of the black box that's over the Rise logo as the slider
            //   bar moves
            // this gives the effect of blinds being pulled downward
            //
            // progress: the value that the slider bar is currently at
            //      default range is 0 to 100
            // look at android monitor to see value of progress bar as you move slider
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ViewGroup.LayoutParams params = blindsView.getLayoutParams();
                params.height = (int) round(1.5*progress);
                blindsView.setLayoutParams(params);
                Log.d("poop", String.valueOf(progress)); //
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);
        return view;

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

    public interface OnFragmentInteractionListener {
    }
}