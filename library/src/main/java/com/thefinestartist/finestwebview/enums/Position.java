package com.thefinestartist.finestwebview.enums;

import java.io.Serializable;

/**
 * Created by Leonardo on 11/14/15.
 */
public enum Position {

    TOP_OF_TOOLBAR,
    BOTTON_OF_TOOLBAR,
    TOP_OF_WEBVIEW,
    BOTTOM_OF_WEBVIEW;

    public static Position fromSerializable(Serializable serializable) {
        if (serializable == null)
            return Position.BOTTON_OF_TOOLBAR;

        return (Position) serializable;
    }
}
