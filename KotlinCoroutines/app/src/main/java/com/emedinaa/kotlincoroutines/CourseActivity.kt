package com.emedinaa.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.model.Course
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.layout_content.*
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
        recyclerView.adapter = adapter

        populate()
        fetchReviews()
    }

    private fun populate(){
        intent?.extras?.getParcelable<Course>("COURSE")?.let {
            collapToolbarLayout.title = it.nickname
            textViewName.text = it.title
            textViewModality.text = it.modality
            textViewDate.text = "Fecha de inicio: ".plus(it.date)
            textViewDesc.text = it.desc
            Glide.with(this).load(it.photo).into(imageView)
        }
    }

    private fun fetchReviews(){
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO){
                repository.fetchReviews()
            }
            adapter.update(result)
        }
    }

}
