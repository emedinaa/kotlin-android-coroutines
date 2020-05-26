package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review
import com.emedinaa.kotlincoroutines.data.http.ApiClient

class RemoteDataSource:DataSource {

    override  fun fetchCourses(): List<Course> {
        return  ApiClient.getCourses()
    }

    override  fun fetchCourseByName(name: String): List<Course> {
        return  ApiClient.getCourses()
    }

    override  fun fetchReviews(): List<Review> {
        return  ApiClient.getReviews()
    }
}