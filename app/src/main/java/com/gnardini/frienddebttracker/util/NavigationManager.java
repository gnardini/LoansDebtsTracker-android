package com.gnardini.frienddebttracker.util;

import android.app.Activity;
import android.content.Intent;

import com.gnardini.frienddebttracker.screen.edit_debt.EditDebtActivity;
import com.gnardini.frienddebttracker.screen.new_debt.NewDebtActivity;

public class NavigationManager {

    public void openNewDebtActivity(Activity activity, boolean isLoan) {
        Intent intent = new Intent(activity, NewDebtActivity.class);
        intent.putExtra(Extras.IS_LOAN, isLoan);
        activity.startActivity(intent);
    }

    public void openEditDebtActivity(Activity activity, long debtId) {
        Intent intent = new Intent(activity, EditDebtActivity.class);
        intent.putExtra(Extras.DEBT_ID, debtId);
        activity.startActivity(intent);
    }

}
