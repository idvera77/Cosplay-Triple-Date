package com.mystra77.visualnovel.fragments;


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


public class GameStartFragment extends Fragment {
    private HomeActivity activity;
    private View view;
    private Button newGame;
    private Intent intentNewGame;

    public GameStartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        activity = (HomeActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_game_start, container, false);

        newGame = view.findViewById(R.id.btnNewGame);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Player player = new Player();
                bundle.putSerializable("player", player);
                intentNewGame = new Intent(view.getContext(), Game.class);
                intentNewGame.putExtras(bundle);
                startActivity(intentNewGame);
            }
        });
        return view;
    }

}
