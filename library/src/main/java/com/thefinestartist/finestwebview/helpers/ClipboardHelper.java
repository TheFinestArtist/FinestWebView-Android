package com.thefinestartist.finestwebview.helpers;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Leonardo on 11/28/15.
 */
public class ClipboardHelper {

    public static void clip(@NonNull Context context, @NonNull String text) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("FinestWebView", text);
            clipboard.setPrimaryClip(clip);
        }
    }
}
