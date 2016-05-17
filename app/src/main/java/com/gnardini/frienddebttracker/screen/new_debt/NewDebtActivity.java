package com.gnardini.frienddebttracker.screen.new_debt;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.activity.BaseActivity;
import com.gnardini.frienddebttracker.util.Extras;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class NewDebtActivity extends BaseActivity implements NewDebtView {

    @BindView(R.id.new_debt_type)
    RadioGroup debtType;

    private NewDebtPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_debt_activity);
        ButterKnife.bind(this);

        presenter = new NewDebtPresenter(this);
        boolean isLoan = getIntent().getBooleanExtra(Extras.IS_LOAN, true);
        debtType.check(isLoan ? R.id.new_is_loan : R.id.new_is_debt);
        presenter.setIsLoan(isLoan);
    }

    @OnClick(R.id.new_is_debt)
    public void onDebtSelected() {
        presenter.setIsLoan(false);
    }

    @OnClick(R.id.new_is_loan)
    public void onLoanSelected() {
        presenter.setIsLoan(true);
    }

    @OnTextChanged(R.id.new_name)
    public void onNameWritten(CharSequence name) {
        presenter.setName(name.toString());
    }

    @OnTextChanged(R.id.new_amount)
    public void onAmountSet(CharSequence amount) {
        float newAmount = TextUtils.isEmpty(amount) ? 0f : Float.parseFloat(amount.toString());
        presenter.setAmount(newAmount);
    }

    @OnClick(R.id.new_debt_save)
    public void onSaveDebt() {
        presenter.save();
    }

    @OnClick(R.id.new_debt_cancel)
    public void onCancel() {
        close();
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void showEmptyNameError() {
        Toast.makeText(this, R.string.error_empty_name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyAmountError() {
        Toast.makeText(this, R.string.error_empty_amount, Toast.LENGTH_SHORT).show();
    }

}
