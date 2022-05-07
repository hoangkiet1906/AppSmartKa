package com.rajendra.onlinedailygroceries.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.rajendra.onlinedailygroceries.R;

public class related_pro_Fragment extends Fragment {

    public related_pro_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_related_pro, container, false);
    }
}