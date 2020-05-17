package com.emedinaa.kotlincoroutines.data

import com.emedinaa.kotlincoroutines.Course

data class CourseResponse(val status:Int?,val msg:String?,val data:List<Course>?){
    fun isSuccess():Boolean= (status==200)
}