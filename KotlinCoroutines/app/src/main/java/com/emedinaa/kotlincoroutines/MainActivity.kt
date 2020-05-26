package com.emedinaa.kotlincoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.executor.AppExecutors
import com.emedinaa.kotlincoroutines.executor.KAppExecutors


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

    private val appExecutor:KAppExecutors by lazy {
        KAppExecutors()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter

        //fetchCoursesSync()
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
        appExecutor.networkIO.execute {
            printThread()
            val courseResult = repository.fetchCourses()
            val reviewResult = repository.fetchReviews()

            appExecutor.mainThread.execute{
                printThread()
                adapter.update(courseResult)
                reviewList = reviewResult
            }
        }
    }

    private fun fetchCoursesSync(){
        printThread()
        val courseResult = repository.fetchCourses()
        printThread()
        adapter.update(courseResult)
    }

    private fun fetchCourses(){
        appExecutor.networkIO.execute {
            printThread()
            val  result = repository.fetchCourses()
            appExecutor.mainThread.execute {
                printThread()
                adapter.update(result)
            }
        }
    }

    private fun printThread(){
        Log.v("CONSOLE", "thread ${Thread.currentThread().id} ${ Thread.currentThread().name }")
    }
}
