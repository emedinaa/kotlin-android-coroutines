package com.emedinaa.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseActivity : AppCompatActivity() {

    private val adapter:ReviewAdapter by lazy {
        ReviewAdapter(emptyList())
    }

    private val repository: Repository by  lazy {
        Repository(RemoteDataSource())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView.adapter = adapter

        fetchReviews()
    }

    private fun fetchReviews(){
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.fetchReviews()
            }
            adapter.update(result)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
