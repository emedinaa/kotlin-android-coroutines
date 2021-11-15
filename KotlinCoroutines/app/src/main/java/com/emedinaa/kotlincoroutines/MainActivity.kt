package com.emedinaa.kotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter

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

    private fun fetchCourses() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.fetchCourses()
            }
            Log.v("CONSOLE","result $result")
            adapter.update(result)
        }
    }

    private fun fetchReviews() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.fetchCourses()
            }
            adapter.update(result)
        }
    }

    private fun fetchData() {
        lifecycleScope.launch {
            val courseDeferred = async(Dispatchers.IO) {
                repository.fetchCourses()
            }
            val reviewDeferred = async(Dispatchers.IO) {
                repository.fetchReviews()
            }

            val courseResult = courseDeferred.await()
            reviewDeferred.await()

            adapter.update(courseResult)
        }
    }

}
