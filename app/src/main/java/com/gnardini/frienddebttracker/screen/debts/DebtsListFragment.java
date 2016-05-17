package com.gnardini.frienddebttracker.screen.debts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnardini.frienddebttracker.DebtsApp;
import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.adapter.DebtsItemAdapter;
import com.gnardini.frienddebttracker.model.Debt;
import com.gnardini.frienddebttracker.util.Extras;
import com.gnardini.frienddebttracker.view.EmptyView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DebtsListFragment extends Fragment implements DebtListView {

    @BindView(R.id.empty_view)
    EmptyView emptyView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    DebtsPresenter debtsPresenter;
    DebtsItemAdapter adapter;

    private boolean isLoan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.debts_list_fragment, container, false);
        ButterKnife.bind(this, rootView);
        debtsPresenter = DebtsApp.getInstance().getDebtsPresenter();
        adapter = new DebtsItemAdapter(debtsPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        isLoan = isLoan(getArguments());
        updateContent();
        return rootView;
    }

    private boolean isLoan(Bundle args) {
        if (args != null) {
            return args.getBoolean(Extras.IS_LOAN, false);
        }
        return false;
    }

    @Override
    public void setDebts(List<Debt> debts) {
        adapter.setItems(debts);
    }

    @Override
    public void showEmptyView(@StringRes int titleRes, @StringRes int subtitleRes) {
        emptyView.setTitle(getString(titleRes));
        emptyView.setSubtitle(getString(subtitleRes));
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    public void updateContent() {
        if (debtsPresenter == null) {
            return;
        }
        if (isLoan) {
            debtsPresenter.loadMyLoans(this);
        } else {
            debtsPresenter.loadMyDebts(this);
        }
    }

}
