package com.woon.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.woon.core.navigation.LocalNavController
import com.woon.detail.model.BookUiModel
import com.woon.detail.state.DetailUiState
import com.woon.detail.ui.ErrorScreen
import com.woon.detail.ui.LoadingScreen
import com.woon.detail.ui.SuccessScreen
import com.woon.detail.ui.screen.BodyScreen
import com.woon.detail.ui.screen.FooterScreen
import com.woon.detail.ui.screen.HeaderScreen

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    id: String
){
    val navController = LocalNavController.current
    val viewModel = hiltViewModel<DetailViewModel>()
    val uiState = viewModel.book.collectAsState().value

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