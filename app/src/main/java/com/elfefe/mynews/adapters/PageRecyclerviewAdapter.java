package com.elfefe.mynews.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;

public class PageRecyclerviewAdapter extends RecyclerView.Adapter<PageRecyclerviewAdapter.PageViewHolder> {

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder pageViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    static class PageViewHolder extends RecyclerView.ViewHolder {
        View view;

        PageViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
        }
    }
}
