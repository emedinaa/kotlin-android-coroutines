package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course
import com.emedinaa.kotlincoroutines.Review

interface DataSource {

    fun fetchCourses():List<Course>

    fun fetchCourseByName(name:String):List<Course>

    fun fetchReviews():List<Review>
}