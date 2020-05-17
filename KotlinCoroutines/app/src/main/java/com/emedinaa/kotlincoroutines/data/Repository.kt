package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course

class Repository(private val dataSource: DataSource) {

    suspend fun fetchCourses():List<Course>{
        return dataSource.fetchCourses()
    }

    suspend fun fetchCoursesByName(name:String):List<Course>{
        return dataSource.fetchCourseByName(name)
    }
}