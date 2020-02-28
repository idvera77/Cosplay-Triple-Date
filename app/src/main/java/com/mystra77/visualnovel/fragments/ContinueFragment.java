package com.mystra77.visualnovel.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mystra77.visualnovel.Game;
import com.mystra77.visualnovel.HomeActivity;
import com.mystra77.visualnovel.R;
import com.mystra77.visualnovel.classes.Player;

import java.util.ArrayList;


public class ContinueFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private Button load1, load2, load3, delete1, delete2, delete3;
    private Intent intentContinueGame;
    private ArrayList<String> fillButton;

    public ContinueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (HomeActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_continue, container, false);

        load1 = view.findViewById(R.id.btnLoadSave1);
        load2 = view.findViewById(R.id.btnLoadSave2);
        load3 = view.findViewById(R.id.btnLoadSave3);
        delete1 = view.findViewById(R.id.btnDeleteLoad1);
        delete2 = view.findViewById(R.id.btnDeleteLoad2);
        delete3 = view.findViewById(R.id.btnDeleteLoad3);


        fillButton = activity.getMoh().fillLoadButton();

        if (!fillButton.get(0).equals(".")) {
            load1.setText(fillButton.get(0));
            load1.setEnabled(true);
            delete1.setEnabled(true);
        }
        if (!fillButton.get(1).equals(".")) {
            load2.setText(fillButton.get(1));
            load2.setEnabled(true);
            delete2.setEnabled(true);
        }
        if (!fillButton.get(2).equals(".")) {
            load3.setText(fillButton.get(2));
            load3.setEnabled(true);
            delete3.setEnabled(true);
        }

        load1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSoundSaveLoad().start();
                Bundle bundle = new Bundle();
                Player player = activity.getMoh().loadGame(1);
                bundle.putSerializable("player", player);
                intentContinueGame = new Intent(view.getContext(), Game.class);
                intentContinueGame.putExtras(bundle);
                startActivity(intentContinueGame);
            }
        });

        load2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSoundSaveLoad().start();
                Bundle bundle = new Bundle();
                Player player = activity.getMoh().loadGame(2);
                bundle.putSerializable("player", player);
                intentContinueGame = new Intent(view.getContext(), Game.class);
                intentContinueGame.putExtras(bundle);
                startActivity(intentContinueGame);
            }
        });

        load3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.getSoundSaveLoad().start();
                Bundle bundle = new Bundle();
                Player player = activity.getMoh().loadGame(3);
                bundle.putSerializable("player", player);
                intentContinueGame = new Intent(view.getContext(), Game.class);
                intentContinueGame.putExtras(bundle);
                startActivity(intentContinueGame);
            }
        });

        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlert(1);
            }
        });

        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlert(2);
            }
        });

        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlert(3);
            }
        });

        return view;
    }

    public void deleteAlert(final int deleteSaveId) {
        activity.getSoundClick().start();
        new AlertDialog.Builder(view.getContext(), R.style.AlertDialogCustom)
                .setMessage(R.string.deleteSaveGame)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.getMoh().deleteSaveGame(deleteSaveId);
                        if (deleteSaveId == 1) {
                            dialog.dismiss();
                            load1.setText(R.string.empty);
                            load1.setEnabled(false);
                            delete1.setEnabled(false);
                        } else if (deleteSaveId == 2) {
                            dialog.dismiss();
                            load2.setText(R.string.empty);
                            load2.setEnabled(false);
                            delete2.setEnabled(false);
                        } else if (deleteSaveId == 3) {
                            dialog.dismiss();
                            load3.setText(R.string.empty);
                            load3.setEnabled(false);
                            delete3.setEnabled(false);
                        }
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

}
