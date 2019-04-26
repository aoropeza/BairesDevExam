package com.example.bairesdevexam.net;

import com.example.bairesdevexam.entities.Repository;

import java.util.List;

public class ResponseSearch {
    List<Repository> repositories;

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }
}
