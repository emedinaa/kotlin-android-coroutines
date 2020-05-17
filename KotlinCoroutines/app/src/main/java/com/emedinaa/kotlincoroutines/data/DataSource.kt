package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course

interface DataSource {

    suspend fun fetchCourses():List<Course>

    suspend fun fetchCourseByName(name:String):List<Course>
}