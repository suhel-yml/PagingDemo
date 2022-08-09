package com.yml.pagingdemo.domain

import com.yml.pagingdemo.data.NewsApi
import com.yml.pagingdemo.model.NewsArticleModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchNewsArticlesByQueryUseCase(
    private val query: String,
    private val page: Int,
    private val pageSize: Int,
    private val newsApi: NewsApi,
    private val dispatcher: CoroutineDispatcher,
) : UseCase<List<NewsArticleModel>> {

    override suspend fun execute(): List<NewsArticleModel> = withContext(dispatcher) {
        if (query.isBlank()) {
            return@withContext emptyList()
        }

        val response = newsApi.getBitcoinNews(query, page, pageSize)

        if (response.status != "ok") {
            throw Exception("Status not \"ok\"")
        }

        response.articles.map { news ->
            NewsArticleModel(
                news.source.name,
                news.title,
                news.description,
                news.url,
                news.publishedAt
            )
        }
    }

    class Factory(
        private val newsApi: NewsApi,
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {

        fun create(
            query: String,
            page: Int,
            pageSize: Int
        ): FetchNewsArticlesByQueryUseCase {
            return FetchNewsArticlesByQueryUseCase(
                query,
                page,
                pageSize,
                newsApi,
                dispatcher
            )
        }

    }

}