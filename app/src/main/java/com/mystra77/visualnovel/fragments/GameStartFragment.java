package com.mystra77.visualnovel.fragments;


import android.app.AlertDialog;
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


public class GameStartFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private Button newGame;
    private Button loadLastSave;
    private Intent intentNewGame;
    private ArrayList<String> unlockLastSave;
    private Bundle bundle;
    private Player player;

    public GameStartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Establish the view
        view = inflater.inflate(R.layout.fragment_game_start, container, false);

        //add the activity of HomeActivity
        activity = (HomeActivity) getActivity();

        //Linking Buttons to variables
        newGame = view.findViewById(R.id.btnNewGame);
        loadLastSave = view.findViewById(R.id.btnLastSave);

        //Using the lastSave function of the database we unlock the loadLastSave button
        unlockLastSave = activity.getMoh().fillLoadButton();
        for (String lastSave : unlockLastSave) {
            if (!lastSave.equals(".")) {
                loadLastSave.setEnabled(true);
            }
        }

        /**
         * Create a new player object
         * The player object is entered into a Bundle that will be called in the game
         * If the game is already completed, an alert will appear warning us of this
         */
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame.setEnabled(false);
                loadLastSave.setEnabled(false);
                activity.getSoundSaveLoad().start();
                Bundle bundle = new Bundle();
                Player player = new Player();
                bundle.putSerializable("player", player);
                intentNewGame = new Intent(view.getContext(), Game.class);
                intentNewGame.putExtras(bundle);
                startActivity(intentNewGame);
            }
        });

        /**
         * Call the database to use the function loadGame
         * It retrieves the saved data and creates a player with it
         * The player object is entered into a Bundle that will be called in the game
         * If the game is already completed, an alert will appear warning us of this
         */
        loadLastSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newGame.setEnabled(false);
                loadLastSave.setEnabled(false);
                activity.getSoundSaveLoad().start();
                bundle = new Bundle();
                player = activity.getMoh().loadLastSave();
                bundle.putSerializable("player", player);
                if (player.getStage() != 5) {
                    intentNewGame = new Intent(view.getContext(), Game.class);
                    intentNewGame.putExtras(bundle);
                    startActivity(intentNewGame);
                } else {
                    new AlertDialog.Builder(view.getContext(), R.style.AlertDialogCustom)
                            .setMessage(R.string.messageGameCompleted)
                            .show();
                    newGame.setEnabled(true);
                }
            }
        });
        return view;
    }

}
