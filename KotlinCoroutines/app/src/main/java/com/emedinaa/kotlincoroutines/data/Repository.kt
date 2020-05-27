package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.model.Course
import com.emedinaa.kotlincoroutines.model.Review

class Repository(private val dataSource: DataSource) {

    suspend fun fetchCourses():List<Course>{
        return dataSource.fetchCourses()
    }

    suspend fun fetchCoursesByName(name:String):List<Course>{
        return dataSource.fetchCourseByName(name)
    }

    suspend fun fetchReviews():List<Review>{
        return dataSource.fetchReviews()
    }
}