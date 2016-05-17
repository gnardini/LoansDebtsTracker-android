package com.gnardini.frienddebttracker.screen.debts;

import android.support.annotation.StringRes;

import com.gnardini.frienddebttracker.model.Debt;

import java.util.List;

public interface DebtListView {

    void setDebts(List<Debt> debts);

    void showEmptyView(@StringRes int titleRes, @StringRes int subtitleRes);

    void hideEmptyView();

}
