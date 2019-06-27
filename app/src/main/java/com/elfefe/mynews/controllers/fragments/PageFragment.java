package com.elfefe.mynews.controllers.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfefe.mynews.R;
import com.elfefe.mynews.controllers.adapters.PageRecyclerviewAdapter;
import com.elfefe.mynews.models.Article;
import com.elfefe.mynews.models.Pages;
import com.elfefe.mynews.utils.PagesAsyncTask;

import java.util.List;
import java.util.Objects;

public class PageFragment extends Fragment implements PagesAsyncTask.Listeners {

    private static final String KEY_PAGE = "KEY_PAGE";

    private RecyclerView recyclerView;
    private Pages page;

    public PageFragment() {
    }

    public static PageFragment newInstance(Pages page) {
        PageFragment frag = new PageFragment();

        Bundle args = new Bundle();
        args.putSerializable(KEY_PAGE, page);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        page = (Pages) (getArguments() != null ? getArguments().getSerializable(KEY_PAGE) : null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_page, container, false);

        recyclerView = result.findViewById(R.id.page_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        DisplayMetrics metrics = new DisplayMetrics();
        Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Log.d("FRAGGGMMEMRMRMRME", metrics.heightPixels+"");

        new PagesAsyncTask(this, metrics.heightPixels).execute(page);

        return result;
    }

    @Override
    public void onResult(List<Article> articles) {
        PageRecyclerviewAdapter adapter = new PageRecyclerviewAdapter(this.getContext(), articles);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
