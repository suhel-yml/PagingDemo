package com.yml.pagingdemo.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yml.pagingdemo.model.NewsArticleModel
import okio.IOException
import retrofit2.HttpException

class NewsDataSource(
    private val query: String,
    private val useCaseFactory: FetchNewsArticlesByQueryUseCase.Factory,
) : PagingSource<Int, NewsArticleModel>() {

    /**
     * This is generic refresh key generator used to generate a key for any page.
     * The last accessed position is used to determine the page boundary
     */
    override fun getRefreshKey(state: PagingState<Int, NewsArticleModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsArticleModel> {
        return try {
            // The key is null when it is used to load for the first time
            val key = params.key ?: FIRST_PAGE_KEY
            val articles = useCaseFactory.create(query, key, params.loadSize).execute()

            // Prev key is valid only if there are pages that exist
            // before the current page
            val prevKey = if (key > FIRST_PAGE_KEY) key - 1 else null

            // Next key is valid if there are possibilities for further pages
            // i.e. when the current page is non empty
            val nextKey = if (articles.isNotEmpty()) key + 1 else null

            LoadResult.Page(
                data = articles,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {

        private const val FIRST_PAGE_KEY = 1

    }

}
