package com.gnardini.frienddebttracker;

import com.gnardini.frienddebttracker.repository.DebtsRepo;
import com.gnardini.frienddebttracker.screen.debts.DebtsPresenter;
import com.gnardini.frienddebttracker.screen.debts.DebtsView;
import com.gnardini.frienddebttracker.util.AlarmScheduler;
import com.gnardini.frienddebttracker.util.NavigationManager;
import com.orm.SugarApp;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class DebtsApp extends SugarApp {

    public static final String APP_SHARED_PREFERENCES =
            "com.gnardini.frienddebttracker.SHARED_PREFERENCES";

    private static DebtsApp instance;

    private DebtsPresenter debtsPresenter;
    private NavigationManager navigationManager;
    private DebtsRepo debtsRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initCalligraphy();
        initAlarmManager();
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void initAlarmManager() {
        AlarmScheduler.setAlarm(instance, false);
    }

    public DebtsRepo getDebtsRepo() {
        if (debtsRepo == null) {
            debtsRepo = new DebtsRepo();
        }
        return debtsRepo;
    }

    // TODO: Replace this with dagger.
    public void initDebtsPresenter(DebtsView view) {
        debtsPresenter = new DebtsPresenter(view);
    }

    public DebtsPresenter getDebtsPresenter() {
        return debtsPresenter;
    }

    public NavigationManager getNavigationManager() {
        if (navigationManager == null) {
            navigationManager = new NavigationManager();
        }
        return navigationManager;
    }

    public static DebtsApp getInstance() {
        return instance;
    }

}
