package com.example.favoritesdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.favoritesdemo.ui.ArticleListScreen
import com.example.favoritesdemo.ui.theme.FavoritesDemoTheme
import com.example.favoritesdemo.viewmodel.ArticleViewModel

class MainActivity :    ComponentActivity() {

    private val viewModel = ArticleViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FavoritesDemoTheme {
                ArticleListScreen(viewModel)
            }
        }
    }
}