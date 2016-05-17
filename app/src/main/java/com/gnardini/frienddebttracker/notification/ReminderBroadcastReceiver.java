package com.gnardini.frienddebttracker.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.gnardini.frienddebttracker.R;
import com.gnardini.frienddebttracker.model.Debt;
import com.gnardini.frienddebttracker.repository.DebtsRepo;
import com.gnardini.frienddebttracker.repository.RepoCallback;
import com.gnardini.frienddebttracker.screen.debts.DebtsActivity;
import com.gnardini.frienddebttracker.util.AlarmScheduler;

import java.util.List;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 550;

    private DebtsRepo debtsRepo = new DebtsRepo();
    private List<Debt> loans;
    private List<Debt> debts;

    @Override
    public void onReceive(final Context context, Intent intent) {
        loans = null;
        debts = null;
        debtsRepo.getLoans(new RepoCallback<List<Debt>>() {
            @Override
            public void onSuccess(List<Debt> value) {
                loans = value;
                checkAllLoaded(context);
            }

            @Override
            public void onError() {}
        });
        debtsRepo.getDebts(new RepoCallback<List<Debt>>() {
            @Override
            public void onSuccess(List<Debt> value) {
                debts = value;
                checkAllLoaded(context);
            }

            @Override
            public void onError() {}
        });
        AlarmScheduler.setAlarm(context, true);
    }

    private void checkAllLoaded(Context context) {
        if (loans != null && debts != null) {
            String title = null;
            int pendingLoans = loans.size();
            int pendingDebts = debts.size();
            if (pendingDebts == 0) {
                if (pendingLoans == 1) {
                    title = context.getString(R.string.notification_one_loan,
                            loans.get(0).getAmount(), loans.get(0).getName());
                } else if (pendingLoans > 1) {
                    title = context.getString(R.string.notification_multiple_loans,
                            pendingLoans);
                }
            } else if (pendingLoans == 0) {
                if (pendingDebts == 1) {
                    title = context.getString(R.string.notification_one_debt,
                            debts.get(0).getAmount(), debts.get(0).getName());
                } else if (pendingDebts > 1) {
                    title = context.getString(R.string.notification_multiple_debts,
                            pendingDebts);
                }
            } else {
                title = context.getString(R.string.notification_loans_and_debts,
                        pendingLoans, pendingDebts);
            }
            if (title != null) {
                sendNotification(context, title);
            }
        }
    }

    private void sendNotification(Context context, String title) {
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.app_icon)
                        .setContentTitle(title)
                        .setContentIntent(getPendingNotificationIntent(context))
                        .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    private PendingIntent getPendingNotificationIntent(Context context) {
        Intent debtActivityIntent = new Intent(context, DebtsActivity.class);
        debtActivityIntent.addFlags(
                Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(
                context,
                0,
                debtActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
