package com.emedinaa.kotlincoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var reviewList:List<Review> = emptyList()

    private val adapter:MainAdapter by lazy {
        MainAdapter(emptyList()){
            goToCourse(it)
        }
    }
    private val repository:Repository by  lazy {
        Repository(RemoteDataSource())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter

        fetchCourses()
        //fetchData()
    }

    private fun goToCourse(course: Course){
        val intent = Intent(this,CourseActivity::class.java)
        intent.putExtras(Bundle().apply {
            putParcelable("COURSE",course)
        })
        startActivity(intent)
    }

    private fun fetchData(){
        lifecycleScope.launch {
            val courseDeferred = async (Dispatchers.IO){
                repository.fetchCourses()
            }
            val reviewDeferred = async(Dispatchers.IO){
                repository.fetchReviews()
            }

            val courseResult = courseDeferred.await()
            val reviewResult = reviewDeferred.await()

            adapter.update(courseResult)
            reviewList = reviewResult
        }
    }

    private fun fetchCourses(){
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.fetchCourses()
            }
            adapter.update(result)
        }
    }

    private fun fetchReviews(){
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.fetchCourses()
            }
            adapter.update(result)
        }
    }
}
