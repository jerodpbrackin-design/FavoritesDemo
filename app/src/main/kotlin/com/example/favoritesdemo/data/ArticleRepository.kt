package com.example.favoritesdemo.data

import com.example.favoritesdemo.model.Article

class ArticleRepository {

    private val articles = listOf(
        Article("1", "Jetpack Compose Basics", "Learn Compose basics."),
        Article("2", "Kotlin Coroutines", "Understand structured concurrency."),
        Article("3", "StateFlow & ViewModel", "Reactive state in Compose."),
        Article("4", "Is this Responsive", "The world may never know")
    )

    fun getArticles(): List<Article> = articles
}