package com.mindorks.framework

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class BookViewModel : ViewModel() {

    private var bookRepository: BookRepository? = null
    private var apiResponse: LiveData<ApiResponse?>? = null

    fun init() {
        bookRepository = BookRepository()
        apiResponse = bookRepository?.getApiResponse()
    }

    fun setApiRequest(website: String?) {
        bookRepository?.setApiRequest(website)
    }

    fun getApiResponse(): LiveData<ApiResponse?>? {
        return apiResponse
    }
}