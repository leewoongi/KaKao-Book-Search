package com.woon.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.woon.core.navigation.LocalNavController
import com.woon.detail.state.DetailUiState
import com.woon.detail.ui.ErrorScreen
import com.woon.detail.ui.LoadingScreen
import com.woon.detail.ui.SuccessScreen

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    id: String
){
    val navController = LocalNavController.current
    val viewModel = hiltViewModel<DetailViewModel>()
    val uiState = viewModel.book.collectAsState().value

    DisposableEffect(Unit) {
        onDispose {

        }
    }

    when(uiState){
        is DetailUiState.Loading -> {
            LoadingScreen()
        }

        is DetailUiState.Success -> {
            SuccessScreen(
                item = uiState.book,
                onBack = { navController.popBackStack() },
                onClickFavorite = uiState.onClickFavorite
            )
        }

        is DetailUiState.Error -> {
            ErrorScreen(
                error = uiState.error,
                onClick = uiState.onRetry
            )
        }
    }
}