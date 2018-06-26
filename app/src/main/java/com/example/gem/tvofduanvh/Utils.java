package com.example.gem.tvofduanvh;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by gem on 6/26/18.
 */

public class Utils {
  private Utils(){
  }

  public static Point getDisplaySize(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    return size;
  }

  public static void showToast(Context context, String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
  }

  public static void showToast(Context context, int resourceId) {
    Toast.makeText(context, context.getString(resourceId), Toast.LENGTH_SHORT).show();
  }

  public static int convertDpToPixel(Context context, int dp) {
    float density = context.getResources().getDisplayMetrics().density;
    return Math.round((float) dp * density);
  }

  public static String formatMillis(int millis) {
    String result = "";
    int hr = millis / 3600000;
    millis %= 3600000;
    int min = millis / 60000;
    millis %= 60000;
    int sec = millis / 1000;
    if (hr > 0) {
      result += hr + ":";
    }
    if (min >= 0) {
      if (min > 9) {
        result += min + ":";
      } else {
        result += "0" + min + ":";
      }
    }
    if (sec > 9) {
      result += sec;
    } else {
      result += "0" + sec;
    }
    return result;
  }

}
