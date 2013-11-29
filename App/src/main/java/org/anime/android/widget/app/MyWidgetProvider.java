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
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_CLICK = "ACTION_CLICK";
    private static final String LOG = "org.anime.android.widget.app";
    private long mStartTime;
    private float mOffset;
    private float mTouchX = -1;
    private float mTouchY = -1;
    private float mCenterX = 0;
    private float mCenterY = 0;
    private Matrix mMatrix = new Matrix();
    //private Paint.FontMetrics mFontMetrics;
    public static int r_y = 0;


    private Paint mPaint = new Paint();
    private Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.ARGB_8888);
    private Canvas canvas = new Canvas(bitmap);
    private Paint.FontMetrics mFontMetrics = mPaint.getFontMetrics();

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
        r_y += 10;
        Log.w(LOG, "count:"+String.valueOf(r_y));
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

        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                MyWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        if(r_y > 100){
            r_y = 0;
        }
        //canvas.drawColor(Color.WHITE);
        //canvas.save();
        canvas.translate(10, r_y);
        // translate (1 point)
        //doDraw(canvas, new float[] { 0, 0 }, new float[] { 5, 5 });
        doDraw(canvas, new float[] { 32, 32, 64, 32 },
                new float[] { 32, 32, 64, 48 });

        remoteView.setImageViewBitmap(R.id.image_c,bitmap);
        // Set the text
        remoteView.setTextViewText(R.id.update, String.valueOf(r_y));

        //create a square end
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
    }

    private void doDraw(Canvas canvas, float src[], float dst[]) {
        //canvas.save();
        mMatrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
        canvas.concat(mMatrix);

        mPaint.setColor(Color.GRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, 64, 64, mPaint);
        canvas.drawLine(0, 0, 64, 64, mPaint);
        canvas.drawLine(0, 64, 64, 0, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        // how to draw the text center on our square
        // centering in X is easy... use alignment (and X at midpoint)
        float x = 64/2;
        // centering in Y, we need to measure ascent/descent first
        float y = 64/2 - (mFontMetrics.ascent + mFontMetrics.descent)/2;
        canvas.drawText(src.length/2 + "", x, y, mPaint);

        //canvas.restore();
    }
}