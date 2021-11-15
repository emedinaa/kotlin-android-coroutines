package com.emedinaa.kotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.databinding.ActivityMainBinding
import com.emedinaa.kotlincoroutines.executor.KAppExecutors

/**
 * @author Eduardo Medina
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter: MainAdapter by lazy {
        MainAdapter(emptyList()) {
            goToCourse(it)
        }
    }

    private val repository: Repository by lazy {
        Repository(RemoteDataSource())
    }

    private val appExecutor: KAppExecutors by lazy {
        KAppExecutors()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter

        //fetchCoursesSync()
        fetchCourses()
        //fetchData()
    }

    private fun goToCourse(course: Course) {
        val intent = Intent(this, CourseActivity::class.java)
        intent.putExtras(Bundle().apply {
            putParcelable("COURSE", course)
        })
        startActivity(intent)
    }

    private fun fetchCoursesSync() {
        printThread()
        val courseResult = repository.fetchCourses()
        printThread()
        adapter.update(courseResult)
    }

    private fun fetchCourses() {
        appExecutor.networkIO.execute {
            printThread()
            val result = repository.fetchCourses()
            appExecutor.mainThread.execute {
                printThread()
                adapter.update(result)
            }
        }
    }

    private fun fetchData() {
        appExecutor.networkIO.execute {
            printThread()
            val courseResult = repository.fetchCourses()
            repository.fetchReviews()

            appExecutor.mainThread.execute {
                printThread()
                adapter.update(courseResult)
            }
        }
    }

    private fun printThread() {
        Log.v("CONSOLE", "thread ${Thread.currentThread().id} ${Thread.currentThread().name}")
    }
}
