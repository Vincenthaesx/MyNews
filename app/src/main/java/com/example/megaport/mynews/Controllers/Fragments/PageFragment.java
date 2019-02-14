package com.example.megaport.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.megaport.mynews.R;
import java.util.Objects;

public class PageFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";

    private View rootView;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int page = getArguments().getInt( ARG_PAGE );
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        switch (Objects.requireNonNull( getArguments() ).getInt(ARG_PAGE))
        {
            case 1: {
                rootView = inflater.inflate(R.layout.fragment_main, container, false);
                break;
            }
            case 2: {
                break;
            }
        }
        return rootView;
    }
}