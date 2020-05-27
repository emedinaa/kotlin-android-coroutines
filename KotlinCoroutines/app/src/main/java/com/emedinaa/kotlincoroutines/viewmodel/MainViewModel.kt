package com.emedinaa.kotlincoroutines.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository:Repository):ViewModel() {

    private val _courses = MutableLiveData<List<Course>>().apply { value = emptyList() }
    val courses: LiveData<List<Course>> = _courses

    init {
        fetchCourses()
    }

    fun fetchCourses(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.fetchCourses()
            }
            _courses.value = result
        }
    }
}