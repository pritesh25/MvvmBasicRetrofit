package com.mindorks.framework.mvvm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mTag = "MainActivity"
    private var viewModel: BookViewModel? = null

    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val bookAdapter = BookAdapter()

        b.fragmentBooksearchSearchResultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bookAdapter
        }

        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel!!.init()
        /*viewModel!!.getVolumesResponseLiveData()!!.observe(this, {
            b.progressBar.visibility = View.GONE
            adapter!!.setResults(it!!.items!! as List<StackModel.Item>)

        })*/

        viewModel!!.getApiResponse()!!.observe(this, { response ->

            response?.let { apiResponse ->
                apiResponse.posts?.let {
                    b.progressBar.visibility = View.GONE
                    bookAdapter.setResults(it.items!! as List<StackModel.Item>)
                }
                apiResponse.error?.let {
                    b.progressBar.visibility = View.GONE
                    Toast.makeText(this, "error = ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }

        })

        viewModel!!.searchVolumes("stackoverflow")


    }
}