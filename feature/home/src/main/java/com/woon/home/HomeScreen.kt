package com.woon.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val viewModel = hiltViewModel<HomeViewModel>()
}