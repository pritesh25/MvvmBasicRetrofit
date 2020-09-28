package com.mindorks.framework.mvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    @GET("questions")
    fun searchVolumes(
        @Query("site") site: String?
    ): Call<StackModel?>?
}