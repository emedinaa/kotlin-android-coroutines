package com.emedinaa.kotlincoroutines.data.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author Eduardo Medina
 */
private const val TAG = "HTTP"

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val t1 = System.nanoTime()
        Log.i(TAG, "Sending request ${request.url} on ${chain.connection()} ${request.headers}")

        val response = chain.proceed(request)

        val t2 = System.nanoTime()
        Log.i(
            TAG,
            "Received response for ${response.request.url} in ${(t2 - t1) / 1e6} ${response.body}"
        )
        return response
    }
}