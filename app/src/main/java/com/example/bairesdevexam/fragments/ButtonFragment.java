package com.example.bairesdevexam.fragments;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.bairesdevexam.R;
import com.example.bairesdevexam.databinding.FragmentButtonBinding;

public class ButtonFragment extends Fragment {

    FragmentButtonBinding binding;

    public static ButtonFragment newInstance(){
        return new ButtonFragment();
    }

    public ButtonFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_button, container, false /* attachToRoot */);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.setPresenter( ButtonFragment.this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showToast(){
        Toast.makeText(getContext(), R.string.show_toast_message, Toast.LENGTH_LONG).show();
    }

    public void showAlert(){

        new AlertDialog.Builder(getContext())
                .setTitle(R.string.app_name)
                .setMessage(R.string.show_alert_message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }
}
