package com.example.bairesdevexam.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.bairesdevexam.payload.SearchPayload;
import com.example.bairesdevexam.net.ApiSingleton;
import com.example.bairesdevexam.net.BaseService;
import com.example.bairesdevexam.net.ResponseSearch;
import retrofit2.Response;

public class SearchLoader extends AsyncTaskLoader<SearchPayload> {
    /**
     * Default constructor for the favorites loader
     * */
    public SearchLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public SearchPayload loadInBackground() {
        try{
            BaseService baseService = ApiSingleton.getApiInterfaceInstance();
            // We execute the request
            Response<ResponseSearch> search = baseService.searchGithub("kotlin").execute();
            if( search != null && search.isSuccessful()) {
                // We return the successful response
                return SearchPayload.buildSuccessPayload( search.body().getRepositories());
            } else {
                // Error in the response from the server
                return SearchPayload.buildErrorPayload();
            }
        }catch ( Exception e ){
            return SearchPayload.buildErrorPayload();
        }
    }

    @Override
    public void deliverResult(SearchPayload data) {
        super.deliverResult( data );
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}
