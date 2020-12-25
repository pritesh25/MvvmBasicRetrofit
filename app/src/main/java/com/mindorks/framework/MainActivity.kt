package com.mindorks.framework

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : BaseActivity() {

    private val mTag = "MainActivity"
    private val viewModel: BookViewModel by viewModels()

    private lateinit var b: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        val bookAdapter = BookAdapter()

        b.progressBar.visibility = View.GONE
        b.fragmentBooksearchSearchResultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bookAdapter
        }

        viewModel.init()
        viewModel.getApiResponse()?.observe(this, { response ->
            response?.let { apiResponse ->
                apiResponse.statusCode?.let { status ->
                    when (status) {
                        200 -> {
                            apiResponse.model?.let {
                                b.progressBar.visibility = View.GONE
                                bookAdapter.setResults(it.items!! as List<StackModel.Item>)
                            }
                        }
                        else -> {
                            apiResponse.responseBody?.let {
                                toasty(this, "error = ${JSONObject(it.string())}")
                            }
                        }
                    }
                } ?: kotlin.run {
                    //not connected to server
                    b.progressBar.visibility = View.GONE
                    toasty(this, "Something went wrong")
                }
            }
        })

        findViewById<Button>(R.id.buttonLoadData).setOnClickListener {
            Log.d(mTag, "isInternet = $isInternet")
            if (isInternet) {
                b.progressBar.visibility = View.VISIBLE
                viewModel.setApiRequest("stackoverflow")
            } else {
                b.progressBar.visibility = View.GONE
                toasty(this, "No internet")
            }
        }

    }


}