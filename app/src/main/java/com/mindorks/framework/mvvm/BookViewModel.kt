package com.mindorks.framework.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookViewModel() : ViewModel() {
    private var bookRepository: BookRepository? = null
    private var volumesResponseLiveData: LiveData<StackModel?>? = null
    private var apiResponse : LiveData<ApiResponse?>? = null

    fun init() {
        bookRepository = BookRepository()
        volumesResponseLiveData = bookRepository!!.getVolumesResponseLiveData()
        apiResponse = bookRepository!!.getApiResponse()
    }

    fun searchVolumes(website: String?) {
        bookRepository!!.searchVolumes(website)
    }

    fun getVolumesResponseLiveData(): LiveData<StackModel?>? {
        return volumesResponseLiveData
    }

    fun getApiResponse(): LiveData<ApiResponse?>? {
        return apiResponse
    }
}