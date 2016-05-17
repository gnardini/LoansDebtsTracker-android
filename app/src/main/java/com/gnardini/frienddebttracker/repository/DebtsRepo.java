package com.gnardini.frienddebttracker.repository;

import android.os.AsyncTask;

import com.gnardini.frienddebttracker.model.Debt;

import java.util.List;

public class DebtsRepo {

    public void getDebts(final RepoCallback<List<Debt>> callback) {
        new AsyncTask<Void, Void, List<Debt>> () {

            @Override
            protected List<Debt> doInBackground(Void[] params) {
                return Debt.find(Debt.class, "loan = ?", "0");
            }

            @Override
            protected void onPostExecute(List<Debt> list) {
                callback.onSuccess(list);
            }
        }.execute();
    }

    public void getLoans(final RepoCallback<List<Debt>> callback) {
        new AsyncTask<Void, Void, List<Debt>> () {

            @Override
            protected List<Debt> doInBackground(Void[] params) {
                return Debt.find(Debt.class, "loan = ?", "1");
            }

            @Override
            protected void onPostExecute(List<Debt> list) {
                callback.onSuccess(list);
            }
        }.execute();
    }

    public void saveDebt(Debt debt) {
        debt.save();
    }

    public void removeDebt(Debt debt) {
        debt.delete();
    }

    public void getDebt(long debtId, RepoCallback<Debt> repoCallback) {
        Debt debt = Debt.findById(Debt.class, debtId);
        if (debt != null) {
            repoCallback.onSuccess(debt);
        } else {
            repoCallback.onError();
        }
    }

}
