package com.example.bairesdevexam.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bairesdevexam.R;
import com.example.bairesdevexam.databinding.FragmentGoogleBinding;

public class GoogleFragment extends Fragment {

    FragmentGoogleBinding binding;

    public static GoogleFragment newInstance(){
        return new GoogleFragment();
    }

    public GoogleFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_google, container, false /* attachToRoot */);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String url="https://www.google.com/";
        binding.webview.loadUrl(url);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
