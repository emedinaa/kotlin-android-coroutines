package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review

/**
 * @author Eduardo Medina
 */
class Repository(private val dataSource: DataSource) {

    fun fetchCourses(): List<Course> {
        return dataSource.fetchCourses()
    }

    fun fetchCoursesByName(name: String): List<Course> {
        return dataSource.fetchCourseByName(name)
    }

    fun fetchReviews(): List<Review> {
        return dataSource.fetchReviews()
    }
}