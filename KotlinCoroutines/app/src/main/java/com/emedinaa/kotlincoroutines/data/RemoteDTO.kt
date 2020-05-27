package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.model.Course
import com.emedinaa.kotlincoroutines.model.Review

data class CourseResponse(val status:Int?,val msg:String?,val data:List<Course>?){
    fun isSuccess():Boolean= (status==200)
}

data class ReviewResponse(val status:Int?,val msg:String?,val data:List<Review>?){
    fun isSuccess():Boolean= (status==200)
}