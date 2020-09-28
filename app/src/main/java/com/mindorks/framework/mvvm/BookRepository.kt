package com.mindorks.framework.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository {
    private val baseUrl = "https://api.stackexchange.com/2.2/"
    private val bookService: BookService
    private val volumesResponseLiveData = MutableLiveData<StackModel?>()
    private val apiResponse = MutableLiveData<ApiResponse>()

    fun searchVolumes(keyword: String?) {
        bookService.searchVolumes(keyword)!!.enqueue(object : Callback<StackModel?> {
            override fun onResponse(
                call: Call<StackModel?>,
                response: Response<StackModel?>
            ) {
                if (response.body() != null) {
                    //setVolumesResponseLiveData(response.body()!!)
                    apiResponse.postValue(ApiResponse(response.body()))
                }
            }

            override fun onFailure(call: Call<StackModel?>, t: Throwable) {
                apiResponse.postValue(ApiResponse(t))
            }
        })
    }

    fun getVolumesResponseLiveData(): LiveData<StackModel?> {
        return volumesResponseLiveData
    }

    fun getApiResponse(): LiveData<ApiResponse?> {
        return apiResponse
    }

    fun setVolumesResponseLiveData(stackModel: StackModel?) {
        volumesResponseLiveData.postValue(stackModel)
    }

    init {
        bookService = RetrofitClient.getClient(baseUrl).create(BookService::class.java)
    }
}