package com.example.favoritesdemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.favoritesdemo.model.Article
import com.example.favoritesdemo.viewmodel.ArticleViewModel


@Composable
fun ArticleListScreen(viewModel: ArticleViewModel) {

    var showFavoritesOnly by remember { mutableStateOf(false) }

    val favoriteIds by viewModel.favoriteArticles.collectAsState(initial = emptySet())

    val articlesToShow = if (showFavoritesOnly) {
        viewModel.allArticles.filter { favoriteIds.contains(it.id) }
    } else viewModel.allArticles

    Column {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Articles", style = MaterialTheme.typography.titleLarge)
            Switch(
                checked = showFavoritesOnly,
                onCheckedChange = { showFavoritesOnly = it }
            )
        }

        LazyColumn {
            items(articlesToShow) { article ->
                ArticleItem(
                    article = article,
                    isFavorite = favoriteIds.contains(article.id),
                    onFavoriteClick = { viewModel.toggleFavorite(article.id) }
                )
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article, isFavorite: Boolean, onFavoriteClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(article.title, style = MaterialTheme.typography.titleMedium)
            Text(article.summary, style = MaterialTheme.typography.bodyMedium)
        }
        IconButton(
            onClick = onFavoriteClick
        ) {

            Surface(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.small
            ) {

                Icon(
                    imageVector =
                        if (isFavorite) Icons.Filled.Star
                        else Icons.Outlined.Star,

                    contentDescription = "Favorite",

                    tint =
                        if (isFavorite)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outline,

                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}