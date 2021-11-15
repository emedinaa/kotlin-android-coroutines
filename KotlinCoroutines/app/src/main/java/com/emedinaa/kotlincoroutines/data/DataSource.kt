package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.model.Course
import com.emedinaa.kotlincoroutines.model.Review

/**
 * @author Eduardo Medina
 */
interface DataSource {

    suspend fun fetchCourses(): List<Course>

    suspend fun fetchCourseByName(name: String): List<Course>

    suspend fun fetchReviews(): List<Review>
}