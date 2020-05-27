package com.emedinaa.kotlincoroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.model.Course
import com.emedinaa.kotlincoroutines.viewmodel.MainViewModel
import com.emedinaa.kotlincoroutines.viewmodel.ViewModelFactory


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val adapter:MainAdapter by lazy {
        MainAdapter(emptyList()){
            goToCourse(it)
        }
    }

    private val repository:Repository by  lazy {
        Repository(RemoteDataSource())
    }

    private lateinit var mainViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //view model
        mainViewModel = ViewModelProvider(this,ViewModelFactory(repository)).get(MainViewModel::class.java)

        //ui
        recyclerView.adapter = adapter

        //observers
        mainViewModel.courses.observe(this, Observer {itList ->
            itList?.let {
                adapter.update(it)
            }
        })

        //fetchCoursesVM()
        //fetchCourses()
    }

    private fun goToCourse(course: Course){
        val intent = Intent(this,CourseActivity::class.java)
        intent.putExtras(Bundle().apply {
            putParcelable("COURSE",course)
        })
        startActivity(intent)
    }

    private fun fetchCoursesVM(){
        mainViewModel.fetchCourses()
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
