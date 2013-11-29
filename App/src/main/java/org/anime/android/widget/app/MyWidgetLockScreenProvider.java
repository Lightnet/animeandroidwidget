package org.anime.android.widget.app;

/**
 * Created by Lightnet on 28/11/13.
 */

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

public class MyWidgetLockScreenProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";
    private static final String LOG = "org.anime.android.widget.app";

    //
    /*
    @Override
    public void onDisabled(Context context){
        Log.w(LOG, "LockScreen onDisabled widgetId");
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context){
        Log.w(LOG, "LockScreen onEnabled widgetId");
        super.onEnabled(context);
    }
    */
    //

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.w(LOG, "LockScreen onUpdate widgetId");

        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                MyWidgetLockScreenProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);



        //create a square start
        Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        float leftx = 20;
        float topy = 20;
        float rightx = 200;
        float bottomy = 200;
        canvas.drawRect(leftx, topy, rightx, bottomy, paint);
        remoteView.setImageViewBitmap(R.id.image_c,bitmap);
        //create a square end

        for (int widgetId : allWidgetIds) {
            //Log.w("widgetId:", String.valueOf(widgetId));
            appWidgetManager.updateAppWidget(widgetId, remoteView);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);
            // Register an onClickListener
            Intent intent = new Intent(context, MyWidgetLockScreenProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            int number = (new Random().nextInt(10));
            // Set the text
            remoteViews.setTextViewText(R.id.update, String.valueOf(number));
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }

        /*
        // Build the intent to call the service
        Intent intent = new Intent(context.getApplicationContext(),
                MyUpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

        // Update the widgets via the service
        context.startService(intent);
        */
        //}
    }
}