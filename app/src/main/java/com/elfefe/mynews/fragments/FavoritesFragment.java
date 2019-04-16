package com.elfefe.mynews.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;

public class FavoritesFragment extends Fragment {

    public FavoritesFragment() { }

    public static FavoritesFragment NewInstance(){
        FavoritesFragment frag = new FavoritesFragment();
        return  frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_favorites, container, false);

        ConstraintLayout layoutView = (ConstraintLayout) result.findViewById(R.id.favorites_layout);

        return result;
    }
}
