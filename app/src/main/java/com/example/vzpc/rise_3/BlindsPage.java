package com.example.vzpc.rise_3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfRenderer;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Toast;

import static java.lang.Math.round;

public class BlindsPage extends Fragment {

    private OnFragmentInteractionListener listener;
    private Button setButton;
    private PopupWindow PopupWindow;
    SharedPreferences sharedPref = null;

    public static BlindsPage newInstance() {
        return new BlindsPage();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blinds_page, container, false);

        // restore seekBar progress and boxHeight
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ImageView blindsView = (ImageView) view.findViewById(R.id.blindsView);
        changeBoxHeight(blindsView, sharedPref.getInt("Blinds Progress", -2));

        SeekBar mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mSeekBar.setProgress(sharedPref.getInt("Blinds Progress", -2));
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
                ImageView blindsView = (ImageView) getView().findViewById(R.id.blindsView);
                changeBoxHeight(blindsView, progress);
                Log.d("poop", String.valueOf(progress)); //
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };
        mSeekBar.setOnSeekBarChangeListener(mSeekBarListener);

        setButton = (Button) view.findViewById(R.id.setBlinds);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeekBar mSeekBar = (SeekBar) getView().findViewById(R.id.seekBar);
                int progress = mSeekBar.getProgress();

                sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("Blinds Progress", progress);
                editor.commit();

                Toast.makeText(getContext(),"Blinds set",
                        Toast.LENGTH_SHORT).show();
            }
        });

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

    // changes the height of the blinds box according to seekBar's progress
    private void changeBoxHeight(ImageView blindsView, int progress){
        ViewGroup.LayoutParams params = blindsView.getLayoutParams();
        params.height = (int) round(3.5*progress);
        blindsView.setLayoutParams(params);
    }
}

