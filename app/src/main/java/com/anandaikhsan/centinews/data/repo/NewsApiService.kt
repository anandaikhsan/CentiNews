package com.anandaikhsan.centinews.data.repo

import androidx.annotation.NonNull
import com.anandaikhsan.centinews.data.model.headline.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    fun getTopHeadlines(@Query("country") country: String?, @Query("category") category: String?, @Query("sources") sources: String?, @Query("q") q: String?, @NonNull @Query("apiKey") apiKey: String, @Query("page") page: Int?, @Query("pageSize") pageSize: Int?): Call<News>

    @GET("v2/everything")
    fun getEverything(@Query("q") q:String, @Query("page") page: Int, @Query("pageSize") pageSize: Int, @Query("language") language: String, @Query("apiKey") apiKey: String): Call<News>
}