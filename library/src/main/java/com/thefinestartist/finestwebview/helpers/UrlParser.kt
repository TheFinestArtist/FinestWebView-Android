package com.thefinestartist.finestwebview.helpers

import java.net.MalformedURLException
import java.net.URL

/**
 * Created by Leonardo on 11/23/15.
 */
object UrlParser {
    @JvmStatic
    fun getHost(url: String): String {
        try {
            return URL(url).host
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return url
    }
}