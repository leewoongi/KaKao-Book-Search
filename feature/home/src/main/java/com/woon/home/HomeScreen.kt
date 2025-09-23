package com.woon.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.woon.core.design.textview.BBadge
import com.woon.core.design.textview.BSearchBar
import com.woon.core.design.textview.BTextView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val viewModel = hiltViewModel<HomeViewModel>()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BSearchBar(

        )
    }
}


//
//BTextView(
//text = "TEST TEST TEST",
//color = Color(0xFF46D369),
//textStyle = TextStyle(
//fontSize = 11.sp
//)
//)
//
//BBadge(
//percent = 50,
//modifier = modifier
//)
//