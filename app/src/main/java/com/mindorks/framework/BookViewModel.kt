package com.mindorks.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class BookViewModel() : ViewModel() {
    private var bookRepository: BookRepository? = null
    private var apiResponse : LiveData<ApiResponse?>? = null

    fun init() {
        bookRepository = BookRepository()
        apiResponse = bookRepository!!.getApiResponse()
    }

    fun searchVolumes(website: String?) {
        bookRepository!!.searchVolumes(website)
    }

    fun getApiResponse(): LiveData<ApiResponse?>? {
        return apiResponse
    }
}