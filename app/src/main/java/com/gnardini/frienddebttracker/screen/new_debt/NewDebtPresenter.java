package com.gnardini.frienddebttracker.screen.new_debt;

import android.text.TextUtils;

import com.gnardini.frienddebttracker.DebtsApp;
import com.gnardini.frienddebttracker.model.Debt;
import com.gnardini.frienddebttracker.repository.DebtsRepo;

public class NewDebtPresenter {

    private final DebtsRepo debtsRepo;
    private NewDebtView view;

    private String name;
    private float amount;
    private boolean isOutgoing;

    public NewDebtPresenter(NewDebtView view) {
        this.view = view;
        debtsRepo = DebtsApp.getInstance().getDebtsRepo();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setIsLoan(boolean outgoing) {
        isOutgoing = outgoing;
    }

    public void save() {
        if (TextUtils.isEmpty(name)) {
            view.showEmptyNameError();
        } else if (amount == 0) {
            view.showEmptyAmountError();
        } else {
            Debt debt = new Debt(amount, name, isOutgoing);
            debtsRepo.saveDebt(debt);
            view.close();
        }
    }

}
