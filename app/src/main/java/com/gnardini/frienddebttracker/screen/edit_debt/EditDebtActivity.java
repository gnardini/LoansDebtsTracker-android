package com.gnardini.frienddebttracker.screen.edit_debt;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.activity.BaseActivity;
import com.gnardini.frienddebttracker.databinding.EditDebtActivityBinding;
import com.gnardini.frienddebttracker.util.Extras;

public class EditDebtActivity extends BaseActivity implements EditDebtView {

    private EditDebtActivityBinding binding;
    private EditDebtViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.edit_debt_activity);

        viewModel = new EditDebtViewModel(this,
                getResources(), getIntent().getLongExtra(Extras.DEBT_ID, 0));
        binding.setDebtViewModel(viewModel);
        binding.editDebtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.onSaveClicked();
            }
        });
        binding.editDebtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    @Override
    public void showToast(@StringRes int stringRes) {
        Toast.makeText(EditDebtActivity.this, stringRes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        finish();
    }

}
