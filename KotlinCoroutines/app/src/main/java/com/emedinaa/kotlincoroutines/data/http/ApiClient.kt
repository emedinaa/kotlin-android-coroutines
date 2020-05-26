package com.emedinaa.kotlincoroutines.data.http

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review
import com.emedinaa.kotlincoroutines.data.CourseResponse
import com.emedinaa.kotlincoroutines.data.ReviewResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request

object ApiClient {

    private const val URL =  "https://obscure-earth-55790.herokuapp.com"
    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    /**
     * val typeToken = object : TypeToken<List<Course>>() {}.type
     * GsonBuilder().create().fromJson<List<Course>>(response.body?.string(),typeToken)
     */
    fun getCourses():List<Course> {
        return try {
            val response = httpClient.newCall(
                buildGetRequest("/api/courses/")
            ).execute()
            if(response.isSuccessful){
                val courseResponse = GsonBuilder().create().fromJson<CourseResponse>(response.body?.string(),
                    CourseResponse::class.java)
                courseResponse.data?: emptyList()
            }else{
                emptyList()
            }
        }catch (e:Exception){
            emptyList()
        }
    }

    fun getReviews():List<Review> {
        return try {
            val response = httpClient.newCall(
                buildGetRequest("/api/reviews/")
            ).execute()
            if(response.isSuccessful){
                val reviewResponse = GsonBuilder().create().fromJson<ReviewResponse>(response.body?.string(),
                    ReviewResponse::class.java)
                reviewResponse.data?: emptyList()
            }else{
                emptyList()
            }
        }catch (e:Exception){
            emptyList()
        }
    }

    private fun buildGetRequest(api:String):Request{
        return Request.Builder().apply {
            get()
            url(URL.plus(api))
            }.build()
    }
}