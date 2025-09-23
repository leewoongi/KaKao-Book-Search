package com.woon.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.woon.core.design.card.BHorizontalCard
import com.woon.core.design.card.BHorizontalSkeletonCard
import com.woon.core.design.card.BVerticalCard
import com.woon.core.design.card.BVerticalSkeletonCard
import com.woon.core.design.textview.BSearchBar
import com.woon.core.design.textview.BTextView
import com.woon.home.state.HomeUiState
import com.woon.home.ui.LoadingScreen
import com.woon.home.ui.SuccessScreen

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
            SuccessScreen()
        }
        is HomeUiState.Error -> {}
    }
}
