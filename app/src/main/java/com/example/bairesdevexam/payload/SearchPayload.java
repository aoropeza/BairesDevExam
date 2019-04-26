package com.example.bairesdevexam.payload;

import com.example.bairesdevexam.entities.Repository;

import java.util.List;

public class SearchPayload {
    private List<Repository> repositories;
    private boolean wasSuccessful;

    public static SearchPayload buildSuccessPayload(List<Repository> repositories) {
        return new SearchPayload( repositories);
    }

    public static SearchPayload buildErrorPayload(){
        return new SearchPayload( false );
    }

    private SearchPayload(List<Repository> repositories){
        this.repositories = repositories;
        wasSuccessful = true;
    }

    private SearchPayload(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public boolean wasSuccessful() {
        return wasSuccessful;
    }
}
