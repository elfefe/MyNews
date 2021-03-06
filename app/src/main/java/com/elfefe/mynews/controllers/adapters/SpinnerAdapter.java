package com.elfefe.mynews.controllers.adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.elfefe.mynews.R;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private final int resource;
    private final List<String> periods;
    private final LayoutInflater inflater;

    public SpinnerAdapter(@NonNull Context context, int resource, List<String> periods) {
        super(context, resource);
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

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        return createItemView(position, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, parent);
    }

    class SpinnerView extends View {

        public SpinnerView(Context context) {
            super(context);
        }
    }

    private View createItemView(int position, ViewGroup parent){
        final View view = inflater.inflate(resource, parent, false);

        TextView textView = view.findViewById(R.id.spinner_item_tv);
        textView.setText(periods.get(position));

        return view;
    }
}
