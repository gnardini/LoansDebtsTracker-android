package com.gnardini.frienddebttracker.screen.debts;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gnardini.frienddebttracker.DebtsApp;
import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.activity.BaseActivity;
import com.gnardini.frienddebttracker.adapter.DebtsAdapter;
import com.gnardini.frienddebttracker.util.NavigationManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DebtsActivity extends BaseActivity implements DebtsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private DebtsPresenter debtsPresenter;
    private DebtsAdapter debtsAdapter;
    private NavigationManager navigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debts_activity);
        ButterKnife.bind(this);

        debtsAdapter = new DebtsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(debtsAdapter);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);

        tabLayout.setSelectedTabIndicatorHeight(0);

        DebtsApp app = DebtsApp.getInstance();
        app.initDebtsPresenter(this);
        debtsPresenter = app.getDebtsPresenter();
        navigationManager = app.getNavigationManager();
    }

    @Override
    public void showEditDebtView(long debtId) {
        navigationManager.openEditDebtActivity(this, debtId);
    }

    @Override
    public void updateList(boolean outgoingList) {
        debtsAdapter.updateList(outgoingList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.debts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                boolean isLoan = tabLayout.getSelectedTabPosition() == 0;
                navigationManager.openNewDebtActivity(this, isLoan);
                return true;
            //case R.id.action_settings:
            //    return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        debtsAdapter.updateLists();
    }

}
