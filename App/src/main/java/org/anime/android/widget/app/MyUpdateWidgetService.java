package org.anime.android.widget.app;

/**
 * Created by Lightnet on 28/11/13.
 */
import java.util.Random;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class MyUpdateWidgetService extends Service {
    private static final String LOG = "org.anime.android.widget.app";

    /*
     * When user press the icon it to do something once touch.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());
        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        ComponentName thisWidget = new ComponentName(getApplicationContext(),
                MyWidgetProvider.class);
        int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
        //Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
        //Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));
        //Log.w("head id:", "init start Service?");
        return Service.START_NOT_STICKY;
    }

    /*
    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(LOG, "Called");
        // create some random data
        Log.w("head id:", "init start?");
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());
        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        ComponentName thisWidget = new ComponentName(getApplicationContext(),
                MyWidgetProvider.class);
        int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
        Log.w(LOG, "From Intent" + String.valueOf(allWidgetIds.length));
        Log.w(LOG, "Direct" + String.valueOf(allWidgetIds2.length));
        for (int widgetId : allWidgetIds) {
            // create some random data
            int number = (new Random().nextInt(100));
            RemoteViews remoteViews = new RemoteViews(this
                    .getApplicationContext().getPackageName(),
                    R.layout.widget_layout);
            Log.w("WidgetExample", String.valueOf(number));
            // Set the text
            remoteViews.setTextViewText(R.id.update,
                    "Random: " + String.valueOf(number));
            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(),
                    MyWidgetProvider.class);
            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    allWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();
        super.onStart(intent, startId);
    }
    */

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}