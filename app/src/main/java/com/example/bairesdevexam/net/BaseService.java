package com.example.bairesdevexam.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseService {
    @GET("/legacy/repos/search/Go")
    Call<ResponseSearch> searchGithub(@Query("language") String language);
}
