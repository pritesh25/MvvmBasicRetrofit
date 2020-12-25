package com.mindorks.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository {
    private val bookService = RetrofitClient.getClient().create(BookService::class.java)
    private val apiResponse = MutableLiveData<ApiResponse>()

    fun setApiRequest(keyword: String?) {
        bookService.searchVolumes(keyword)?.enqueue(object : Callback<StackModel?> {
            override fun onResponse(
                call: Call<StackModel?>,
                response: Response<StackModel?>
            ) {
                response.body()?.let {
                    apiResponse.postValue(ApiResponse(it, response.code()))
                } ?: kotlin.run {
                    response.errorBody()?.let {
                        apiResponse.postValue(ApiResponse(it, response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<StackModel?>, t: Throwable) {
                apiResponse.postValue(ApiResponse(t))
            }
        })
    }

    fun getApiResponse(): LiveData<ApiResponse?> {
        return apiResponse
    }
}