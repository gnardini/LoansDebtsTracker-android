package com.gnardini.frienddebttracker.screen.debts;

import com.gnardini.frienddebttracker.DebtsApp;
import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.model.Debt;
import com.gnardini.frienddebttracker.repository.DebtsRepo;
import com.gnardini.frienddebttracker.repository.RepoCallback;

import java.util.List;

public class DebtsPresenter {

    private final DebtsView view;
    private final DebtsRepo debtsRepo;

    public DebtsPresenter(DebtsView view) {
        this.view = view;
        this.debtsRepo = DebtsApp.getInstance().getDebtsRepo();
    }

    public void loadMyDebts(final DebtListView view) {
        debtsRepo.getDebts(new RepoCallback<List<Debt>>() {
            @Override
            public void onSuccess(List<Debt> debtsList) {
                if (debtsList.isEmpty()) {
                    view.showEmptyView(R.string.empty_debts_title, R.string.empty_debts_subtitle);
                } else {
                    view.hideEmptyView();
                    view.setDebts(debtsList);
                }
            }

            @Override
            public void onError() {
            }
        });
    }

    public void loadMyLoans(final DebtListView view) {
        debtsRepo.getLoans(new RepoCallback<List<Debt>>() {
            @Override
            public void onSuccess(List<Debt> debtsList) {
                if (debtsList.isEmpty()) {
                    view.showEmptyView(R.string.empty_loans_title, R.string.empty_loans_subtitle);
                } else {
                    view.hideEmptyView();
                    view.setDebts(debtsList);
                }
            }

            @Override
            public void onError() {
            }
        });
    }

    public void editDebt(Debt debt) {
        view.showEditDebtView(debt.getId());
    }

    public void deleteDebt(Debt debt) {
        debtsRepo.removeDebt(debt);
        view.updateList(debt.isLoan());
    }

}
