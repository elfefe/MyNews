package com.elfefe.mynews.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;

import com.elfefe.mynews.controllers.adapters.SpinnerAdapter;

public class SearchSpinner {

    private final View view;
    private final AppCompatSpinner spinner;

    public SearchSpinner(int spinnerView, ViewGroup layout, int inflateView, LayoutInflater inflater) {
        view = inflater.inflate(inflateView,layout,false);
        spinner = view.findViewById(spinnerView);
    }

    public String getSelectedItem(){
        return spinner.getSelectedItem().toString();
    }

    public View getView() {
        return view;
    }
    public void setTextView(int textViewRes, String title) {
        TextView textView = view.findViewById(textViewRes);
        textView.setText(title);
    }
    public void setAdapter(SpinnerAdapter adapter) {
        spinner.setAdapter(adapter);
    }
}
