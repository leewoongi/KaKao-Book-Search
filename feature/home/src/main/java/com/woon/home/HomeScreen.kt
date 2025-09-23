package com.woon.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.woon.home.state.HomeUiState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val viewModel = hiltViewModel<HomeViewModel>()
    val state = viewModel.uiState.collectAsState().value

    when(state){
        is HomeUiState.Loading -> {
            LoadingScreen()
        }
        is HomeUiState.Success -> {
            SuccessScreen(
                books = state.books,
                topDiscountedBooks = state.topDiscountedBooks
            )
        }
        is HomeUiState.Error -> {}
    }
}
