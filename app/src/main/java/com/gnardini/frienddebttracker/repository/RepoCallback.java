package com.gnardini.frienddebttracker.repository;


public interface RepoCallback<T> {

    void onSuccess(T value);

    void onError();

}
