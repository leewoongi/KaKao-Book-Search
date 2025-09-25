package com.woon.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.woon.core.navigation.LocalNavController
import com.woon.core.navigation.LocalSnackBarHostState
import com.woon.detail.state.DetailUiState
import com.woon.detail.ui.ErrorScreen
import com.woon.detail.ui.LoadingScreen
import com.woon.detail.ui.SuccessScreen
import com.woon.domain.book.exception.BookException

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    id: String
){
    val navController = LocalNavController.current
    val snackBarHostState = LocalSnackBarHostState.current

    val viewModel = hiltViewModel<DetailViewModel>()
    val uiState = viewModel.book.collectAsState().value

    DisposableEffect(Unit) {
        onDispose {
            viewModel.remove()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.snackBar.collect { error ->
            val message = when (error) {
                is BookException.UpdateFailure -> "업데이트에 실패했습니다."
                is BookException.NotFound -> "일치하는 정보가 없습니다."
                else -> "알 수 없는 오류가 발생했습니다."
            }
            snackBarHostState.showSnackbar(message)
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