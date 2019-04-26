package com.example.bairesdevexam.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bairesdevexam.adapters.RepositoriesAdapter;
import com.example.bairesdevexam.interfaces.IRowClicked;
import com.example.bairesdevexam.loader.SearchLoader;
import com.example.bairesdevexam.entities.Repository;
import com.example.bairesdevexam.payload.SearchPayload;
import com.example.bairesdevexam.R;
import com.example.bairesdevexam.databinding.FragmentListBinding;

import java.util.List;

public class ListFragment extends Fragment implements LoaderManager.LoaderCallbacks<SearchPayload>, IRowClicked {

    private static final int LOADER_ID = 546;
    private ProgressDialog progressDialog;
    private FragmentListBinding binding;

    public static ListFragment newInstance(){
        return new ListFragment();
    }

    public ListFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_list, container, false /* attachToRoot */);
        return binding.getRoot();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if( this.isOnline() ){
            getLoaderManager().initLoader( LOADER_ID, null, ListFragment.this);
        }
        else{
            Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public Loader<SearchPayload> onCreateLoader(int id, Bundle args) {
        progressDialog = ProgressDialog.show(getContext(), "",getString(R.string.wait), true);
        return new SearchLoader( getContext() );
    }

    @Override
    public void onLoadFinished(Loader<SearchPayload> loader, SearchPayload data) {
        // We handle the result
        if( data.wasSuccessful() ){
            List<Repository> repositories = data.getRepositories();

            // We check if we have any result
            if( repositories != null && !repositories.isEmpty() ){
                RepositoriesAdapter repositoriesAdapter = new RepositoriesAdapter(getContext(), repositories, this);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                binding.recyclerView.setLayoutManager(layoutManager);
                binding.recyclerView.setAdapter(repositoriesAdapter);
            }
            else{
                Toast.makeText(getContext(), R.string.gnral_error, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getContext(), R.string.gnral_error, Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
    }


    @Override
    public void onLoaderReset(Loader<SearchPayload> loader) {
    }

    @Override
    public void rowClicked(int position, Object model) {
        Repository repository = (Repository) model;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse( repository.url ));
        startActivity(intent);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
