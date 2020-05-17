package com.emedinaa.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val adapter:CourseAdapter by lazy {
        CourseAdapter(emptyList())
    }
    private val repository:Repository by  lazy {
        Repository(RemoteDataSource())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter

        fetchCourses()
    }

    private fun fetchCourses(){
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.fetchCourses()
            }
            adapter.update(result)
        }
    }
}
