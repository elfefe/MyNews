package com.elfefe.mynews.controllers;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;

import com.elfefe.mynews.controllers.adapters.SpinnerAdapter;

public class SearchSpinner {

    private View view;
    private AppCompatSpinner spinner;
    private TextView textView;

    private SpinnerAdapter adapter;

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

    public void setView(View view) {
        this.view = view;
    }

    public AppCompatSpinner getSpinner() {
        return spinner;
    }

    public void setSpinner(int spinnerViewRes) {
        this.spinner = view.findViewById(spinnerViewRes);
    }

    public TextView getTextView() {
        try{
            return textView;
        }catch (Exception e){
            Log.e("error", e.getMessage(),e.getCause());
        }
        return null;
    }

    public void setTextView(int textViewRes, String title) {
        this.textView = view.findViewById(textViewRes);
        this.textView.setText(title);
    }

    public SpinnerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SpinnerAdapter adapter) {
        this.adapter = adapter;
        spinner.setAdapter(adapter);
    }
}
