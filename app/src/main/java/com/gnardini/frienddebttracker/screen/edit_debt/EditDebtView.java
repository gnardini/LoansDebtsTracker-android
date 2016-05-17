package com.gnardini.frienddebttracker.screen.edit_debt;

import android.support.annotation.StringRes;

public interface EditDebtView {

    void showToast(@StringRes int stringRes);

    void close();

}
