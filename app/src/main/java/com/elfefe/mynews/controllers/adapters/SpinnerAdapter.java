package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.elfefe.mynews.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {
    @NonNull
    private final Context context;
    private int resource;
    private List<String> periods;
    private LayoutInflater inflater;

    public SpinnerAdapter(@NonNull Context context, int resource, List<String> periods) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.periods = periods;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return periods.size();
    }

    @Override
    public String getItem(int position) {
        return periods.get(position);
    }

    @NotNull
    @Override
    public View getView(int position, View view, @NotNull ViewGroup parent) {
        return createItemView(position, view, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    class SpinnerView extends View {

        public SpinnerView(Context context) {
            super(context);
        }
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = inflater.inflate(resource, parent, false);

        TextView textView = view.findViewById(R.id.spinner_item_tv);
        textView.setText(periods.get(position));

        return view;
    }
}
