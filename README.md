# Android Paging 3 Demo

## What it showcases
 - Offset based paging from an API with customized pre-fetch distance for seamless scroll
 - Use [`ConcatAdapter`](https://developer.android.com/reference/androidx/recyclerview/widget/ConcatAdapter) to concatenate static and paginated data in one `RecyclerView`
 - Use [`LoadState`](https://developer.android.com/reference/kotlin/androidx/paging/LoadState) to show different indicators for different UI states (loading, error, empty, etc.)
 - Use [`insertSeparators()`](https://developer.android.com/topic/libraries/architecture/paging/v3-transform#separators) to compute and insert date headers by grouping items

## Setup
In [`NewsApi.kt`](app/src/main/java/com/yml/pagingdemo/data/NewsApi.kt) add your API key from [News API](https://newsapi.org/) to
```Kotlin
@Headers("X-Api-Key: <put your api key here>")
```
