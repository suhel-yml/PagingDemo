package com.yml.pagingdemo.data

import com.yml.pagingdemo.model.NewsArticlesResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    @Headers("X-Api-Key: <put your api key here>")
    suspend fun getBitcoinNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): NewsArticlesResponseModel

    companion object {

        fun create(): NewsApi {
            return NetworkUtil
                .createRetrofit("https://newsapi.org/v2/")
                .create(NewsApi::class.java)
        }

    }

}