package com.emedinaa.kotlincoroutines


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emedinaa.kotlincoroutines.data.RemoteDataSource
import com.emedinaa.kotlincoroutines.data.Repository
import com.emedinaa.kotlincoroutines.databinding.ActivityMainBinding
import com.emedinaa.kotlincoroutines.model.Course
import com.emedinaa.kotlincoroutines.viewmodel.MainViewModel
import com.emedinaa.kotlincoroutines.viewmodel.ViewModelFactory
import kotlinx.coroutines.Dispatchers
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

    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory(repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()

        //observers
        mainViewModel.courses.observe(this, { itList ->
            itList?.let {
                adapter.update(it)
            }
        })

        //fetchCoursesVM()
        //fetchCourses()
    }

    private fun setupUi() {
        binding.recyclerView.adapter = adapter
    }

    private fun goToCourse(course: Course) {
        val intent = Intent(this, CourseActivity::class.java)
        intent.putExtras(Bundle().apply {
            putParcelable("COURSE", course)
        })
        startActivity(intent)
    }

    private fun fetchCoursesVM() {
        mainViewModel.fetchCourses()
    }

    private fun fetchCourses() {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.fetchCourses()
            }
            adapter.update(result)
        }
    }
}
