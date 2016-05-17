package com.gnardini.frienddebttracker.screen.edit_debt;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.gnardini.frienddebttracker.DebtsApp;
import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.bindable.TextWatcherAdapter;
import com.gnardini.frienddebttracker.model.Debt;
import com.gnardini.frienddebttracker.repository.DebtsRepo;
import com.gnardini.frienddebttracker.repository.RepoCallback;

public class EditDebtViewModel extends BaseObservable {

    private final Resources resources;
    private final EditDebtView editDebtView;
    private final DebtsRepo debtsRepo;
    private String title;
    private Debt debt;

    public EditDebtViewModel(EditDebtView editDebtView, Resources resources, long debtId) {
        this.editDebtView = editDebtView;
        this.resources = resources;
        this.debtsRepo = DebtsApp.getInstance().getDebtsRepo();
        debtsRepo.getDebt(debtId, new RepoCallback<Debt>() {

            @Override
            public void onSuccess(Debt debt) {
                EditDebtViewModel.this.debt = debt;
                updateView();
            }

            @Override
            public void onError() {
            }
        });
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getName() {
        return debt == null ? "" : debt.getName();
    }

    @Bindable
    public Float getAmount() {
        return debt == null ? 0 : debt.getAmount();
    }

    @Bindable
    public TextWatcher getOnNameChanged() {
        return new TextWatcherAdapter() {
            @Override
            public void onTextChanged(String name) {
                if (debt != null && !name.equals(debt.getName())) {
                    debt.setName(name);
                }
            }
        };
    }

    @Bindable
    public TextWatcher getOnAmountChanged() {
        return new TextWatcherAdapter() {
            @Override
            public void onTextChanged(String amount) {
                if (debt != null) {
                    float newAmount = TextUtils.isEmpty(amount) ? 0f : Float.parseFloat(amount);
                    if (newAmount != debt.getAmount()) {
                        debt.setAmount(newAmount);
                    }
                }
            }
        };
    }

    private void updateView() {
        title = resources.getString(debt.isLoan()
                ? R.string.edit_loan_title : R.string.edit_debt_title);
        notifyChange();
    }

    public void onSaveClicked() {
        if (TextUtils.isEmpty(debt.getName())) {
            editDebtView.showToast(R.string.error_empty_name);
        } else if (debt.getAmount() == 0) {
            editDebtView.showToast(R.string.error_empty_amount);
        } else {
            debtsRepo.saveDebt(debt);
            editDebtView.close();
        }
    }

}
