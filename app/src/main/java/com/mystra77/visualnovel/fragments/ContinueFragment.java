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
    private Button load1;
    private Button load2;
    private Button load3;
    private Button delete1;
    private Button delete2;
    private Button delete3;
    private Intent intentContinueGame;
    private ArrayList<String> fillButton;
    private Bundle bundle;
    private Player player;

    public ContinueFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Establish the view
        view = inflater.inflate(R.layout.fragment_continue, container, false);

        //add the activity of HomeActivity
        activity = (HomeActivity) getActivity();

        //Linking Buttons to variables
        load1 = view.findViewById(R.id.btnLoadSave1);
        load2 = view.findViewById(R.id.btnLoadSave2);
        load3 = view.findViewById(R.id.btnLoadSave3);
        delete1 = view.findViewById(R.id.btnDeleteLoad1);
        delete2 = view.findViewById(R.id.btnDeleteLoad2);
        delete3 = view.findViewById(R.id.btnDeleteLoad3);

        //Call the database to use the function that fills in the text of the load buttons
        fillButton = activity.getMoh().fillLoadButton();

        /*
        If the bd detects that no game has been saved it will leave the default text on the buttons which is Empty,
        otherwise it will fill it in with the data obtained.
        It also unlocks the load and delete button
        */
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

        /**
         * Button1 Load event
         * It disables itself, starts the load game sound and starts the loadGame function
         */
        load1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load1.setEnabled(false);
                activity.getSoundSaveLoad().start();
                loadGame(1);
            }
        });

        /**
         * Button2 Load event
         * It disables itself, starts the load game sound and starts the loadGame function
         */
        load2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load2.setEnabled(false);
                activity.getSoundSaveLoad().start();
                loadGame(2);
            }
        });

        /**
         * Button3 Load event
         * It disables itself, starts the load game sound and starts the loadGame function
         */
        load3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load3.setEnabled(false);
                activity.getSoundSaveLoad().start();
                loadGame(3);
            }
        });

        /**
         * Button1 Delete event
         * Activate the deleteAlert function
         */
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlert(1);
            }
        });

        /**
         * Button2 Delete event
         * Activate the deleteAlert function
         */
        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlert(2);
            }
        });

        /**
         * Button3 Delete event
         * Activate the deleteAlert function
         */
        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAlert(3);
            }
        });

        return view;
    }

    /**
     * Call the database to use the function loadGame
     * It retrieves the saved data and creates a player with it
     * The player object is entered into a Bundle that will be called in the game
     * If the game is already completed, an alert will appear warning us of this
     *
     * @param loadFile Indicates the number of the save game to be loaded
     */
    public void loadGame(int loadFile) {
        bundle = new Bundle();
        player = activity.getMoh().loadGame(loadFile);
        bundle.putSerializable("player", player);
        if (player.getStage() != 5) {
            intentContinueGame = new Intent(view.getContext(), Game.class);
            intentContinueGame.putExtras(bundle);
            startActivity(intentContinueGame);
        } else {
            new AlertDialog.Builder(view.getContext(), R.style.AlertDialogCustom)
                    .setMessage(R.string.messageGameCompleted)
                    .show();
        }
    }

    /**
     * Call the database to use the function deleteSave
     * Using the id recognizes the game and removes it
     * It generates an alert dialog to confirm if we want to delete the saved game.
     * When you accept, the dialog closes and the default text of the button
     *
     * @param deleteSaveId Indicates the id of the save game to be deleted
     */
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
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

}
