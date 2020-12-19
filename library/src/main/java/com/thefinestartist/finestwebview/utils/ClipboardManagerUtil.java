package com.thefinestartist.finestwebview.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * ClipboardManagerUtil helps to manage {@link ClipboardManager} conveniently.
 *
 * @author Leonardo Taehwan Kim
 */
public class ClipboardManagerUtil {

    android.text.ClipboardManager clipboardManager;

    // No Instance
    private ClipboardManagerUtil(Context mContext) {
        clipboardManager = (android.text.ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public void setText(CharSequence text) {
        if (APILevel.require(11)) {
            ClipboardManager cm = (ClipboardManager) clipboardManager;
            ClipData clip = ClipData.newPlainText("ClipboardManagerUtil", text);
            cm.setPrimaryClip(clip);
        } else {
            clipboardManager.setText(text);
        }
    }

    public boolean hasText() {
        if (APILevel.require(11)) {
            ClipboardManager cm = (ClipboardManager) clipboardManager;
            ClipDescription description = cm.getPrimaryClipDescription();
            ClipData clipData = cm.getPrimaryClip();
            return clipData != null
                    && description != null
                    && (description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN));
        } else {
            return clipboardManager.hasText();
        }
    }

    public CharSequence getText() {
        if (APILevel.require(11)) {
            ClipboardManager cm = (ClipboardManager) clipboardManager;
            ClipDescription description = cm.getPrimaryClipDescription();
            ClipData clipData = cm.getPrimaryClip();
            if (clipData != null
                    && description != null
                    && description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
                return clipData.getItemAt(0).getText();
            else
                return null;
        } else {
            return clipboardManager.getText();
        }
    }
}
