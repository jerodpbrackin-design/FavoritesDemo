package com.example.favoritesdemo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.favoritesdemo.data.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArticleViewModel : ViewModel() {

    private val repository = ArticleRepository()
    val allArticles = repository.getArticles()

    private val _favoriteArticles = MutableStateFlow<Set<String>>(emptySet())
    val favoriteArticles: StateFlow<Set<String>> = _favoriteArticles.asStateFlow()

    fun isFavorite(articleId: String): Boolean {
        return _favoriteArticles.value.contains(articleId)
    }

    fun toggleFavorite(articleId: String) {
        _favoriteArticles.update { current ->
            val newSet = current.toMutableSet()
            if (newSet.contains(articleId)) newSet.remove(articleId) else newSet.add(articleId)
            newSet
        }
    }
}