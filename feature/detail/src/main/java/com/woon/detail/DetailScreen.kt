package com.woon.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woon.detail.ui.screen.BodyScreen
import com.woon.detail.ui.screen.FooterScreen
import com.woon.detail.ui.screen.HeaderScreen

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderScreen()

        Spacer(modifier = Modifier.height(16.dp))

        BodyScreen()

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        FooterScreen()
    }
}