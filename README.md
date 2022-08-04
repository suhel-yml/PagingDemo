# Android Paging 3 Demo

## What is it
 - Showcase offset based paging from an API with customized pre-fetch distance for seamless scroll
 - Use [`ConcatAdapter`](https://developer.android.com/reference/androidx/recyclerview/widget/ConcatAdapter) to concatenate static and paginated data in one `RecyclerView`
 - Use [`LoadState`](https://developer.android.com/reference/kotlin/androidx/paging/LoadState) to show different indicators for different UI states (loading, error, empty, etc.)

## Setup
In [`NewsApi.kt`](https://github.com/suhel-yml/PagingDemo/blob/master/app/src/main/java/com/yml/pagingdemo/data/NewsApi.kt) add your API key from [News API](newsapi.org) to
```Kotlin
@Headers("X-Api-Key: <put your api key here>")
```