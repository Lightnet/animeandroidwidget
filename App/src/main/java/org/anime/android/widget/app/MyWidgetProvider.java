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
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";
    private static final String LOG = "org.anime.android.widget.app";

    public void onDisabled(Context context){
        Log.w("widgetId:", "onDisabled widgetId");
        super.onDisabled(context);
    }

    public void onEnabled(Context context){
        Log.w(LOG, "enable widgetId");
        super.onEnabled(context);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int widgetId : appWidgetIds) {
            Log.w(LOG, "widgetId:"+String.valueOf(widgetId));

        }
        //Log.w(LOG, String.valueOf(num_lip));
        // Create some random data
        //int num_head = (new Random().nextInt(2));
        //int num_eye = (new Random().nextInt(10));
        //int num_lip = (new Random().nextInt(11));

        //Log.w("head id:", String.valueOf(num_head));
        //Log.w("eye id:", String.valueOf(num_eye));
        //Log.w("lip id:", String.valueOf(num_lip));

        //Log.w("lip id:", String.valueOf(num_lip));

        //RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        //ImageView drawingImageView = new ImageView(R.id.image_c);





        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        //remoteView.setInt(R.id.image_c, "setMinimumHeight", 300);
        //remoteView.setInt(R.id.image_c, "setMinimumHeight", 300);
        //remoteView.setInt(R.id.image_c, "setVisibility", View.GONE);
        //Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);

        //{
        //create a square start
        /*
        Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        float leftx = 20;
        float topy = 20;
        float rightx = 50;
        float bottomy = 50;
        canvas.drawRect(leftx, topy, rightx, bottomy, paint);
        remoteView.setImageViewBitmap(R.id.image_c,bitmap);
        */
        //create a square end
        //}

        //{

        for (int widgetId : allWidgetIds) {
            Log.w("widgetId:", String.valueOf(widgetId));
            appWidgetManager.updateAppWidget(widgetId, remoteView);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);
            // Register an onClickListener
            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }

        // Build the intent to call the service
        Intent intent = new Intent(context.getApplicationContext(),
                MyUpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

        // Update the widgets via the service
        context.startService(intent);
        //}
    }
}