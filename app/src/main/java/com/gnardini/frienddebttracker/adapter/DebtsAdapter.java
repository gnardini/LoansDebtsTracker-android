package com.gnardini.frienddebttracker.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gnardini.frienddebttracker.screen.debts.DebtsListFragment;
import com.gnardini.frienddebttracker.util.Extras;

public class DebtsAdapter extends FragmentStatePagerAdapter {

    private static final int LOANS_TAB = 0;
    private static final int DEBTS_TAB = 1;

    private final DebtsListFragment loansFragment;
    private final DebtsListFragment debtsFragment;

    public DebtsAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        loansFragment = createFragment(LOANS_TAB);
        debtsFragment = createFragment(DEBTS_TAB);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case LOANS_TAB:
                return loansFragment;
            case DEBTS_TAB:
                return debtsFragment;
        }
        return null;
    }

    private DebtsListFragment createFragment(int position) {
        Bundle args = new Bundle();
        switch (position) {
            case LOANS_TAB:
                args.putBoolean(Extras.IS_LOAN, true);
                break;
            case DEBTS_TAB:
                args.putBoolean(Extras.IS_LOAN, false);
        }
        DebtsListFragment debtsListFragment = new DebtsListFragment();
        debtsListFragment.setArguments(args);
        return debtsListFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case LOANS_TAB:
                return "My loans";
            case DEBTS_TAB:
                return "My debts";
            default:
                return "";
        }
    }

    public void updateLists() {
        loansFragment.updateContent();
        debtsFragment.updateContent();
    }

    public void updateList(boolean outgoingList) {
        if (outgoingList) {
            loansFragment.updateContent();
        } else {
            debtsFragment.updateContent();
        }
    }

}
