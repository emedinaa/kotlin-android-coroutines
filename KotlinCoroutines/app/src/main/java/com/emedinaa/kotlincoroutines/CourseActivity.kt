package com.emedinaa.kotlincoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.databinding.ActivityCourseBinding
import com.emedinaa.kotlincoroutines.model.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Eduardo Medina
 */
class CourseActivity : AppCompatActivity() {

    private val adapter: ReviewAdapter by lazy {
        ReviewAdapter(emptyList())
    }

    private val repository: Repository by lazy {
        Repository(RemoteDataSource())
    }
    private lateinit var binding: ActivityCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.content.recyclerView.adapter = adapter

        populate()
        fetchReviews()
    }

    private fun populate() {
        intent?.extras?.getParcelable<Course>("COURSE")?.let {
            binding.collapToolbarLayout.title = it.nickname
            binding.content.textViewName.text = it.title
            binding.content.textViewModality.text = it.modality
            binding.content.textViewDate.text = "Fecha de inicio: ".plus(it.date)
            binding.content.textViewDesc.text = it.desc
            Glide.with(this).load(it.photo).into(binding.imageView)
        }
    }

    private fun fetchReviews() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.fetchReviews()
            }
            adapter.update(result)
        }
    }

}
