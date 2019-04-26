package com.example.bairesdevexam.net;

import com.example.bairesdevexam.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSingleton {
    private static final long CONNECT_TIMEOUT_MILLIS = 100L * 1000L; // 15 seconds
    private static final long READ_TIMEOUT_MILLIS = 60L * 1000L;    // 20 seconds

    // Reference of the tupperware service
    private static BaseService sBaseService;

    private ApiSingleton() {
        /* Do nothing */
    }

    /**
     * Returns the current domain for the app
     */
    public static String getDomain() {
        return BuildConfig.DOMAIN;
    }

    /**
     * Creates a rest adapter instance and holds it as long as the current process is not killed.
     * Supports http cache.
     *
     * @return RestAdapter for the BaseService interface
     */
    public synchronized static BaseService getApiInterfaceInstance() {
        if (sBaseService == null) {
            /* setting the http client */
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
            httpClient.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);

            // Just log in debug mode
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

            /* GSON json parser for the app */
            Gson gson = new GsonBuilder().create();

            // Creating the API instance
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getDomain())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();

            sBaseService = retrofit.create(BaseService.class);
        }
        return sBaseService;
    }
}
