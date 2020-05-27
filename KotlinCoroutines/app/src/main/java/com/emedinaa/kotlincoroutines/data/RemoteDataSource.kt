package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.model.Course
import com.emedinaa.kotlincoroutines.model.Review
import com.emedinaa.kotlincoroutines.data.http.ApiClient

class RemoteDataSource:DataSource {

    override suspend fun fetchCourses(): List<Course> {
        return  ApiClient.getCourses()
    }

    override suspend fun fetchCourseByName(name: String): List<Course> {
        return  ApiClient.getCourses()
    }

    override suspend fun fetchReviews(): List<Review> {
        return  ApiClient.getReviews()
    }
}