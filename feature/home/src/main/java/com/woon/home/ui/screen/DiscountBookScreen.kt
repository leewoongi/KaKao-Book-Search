package com.woon.home.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.card.BVerticalCard
import com.woon.core.design.textview.BTextView
import com.woon.home.model.BookUiModel

@Composable
fun DisCountBookScreen(
    modifier : Modifier = Modifier,
    item : List<BookUiModel>
){
    Column(
        modifier = modifier
    ) {
        BTextView(
            text = "오늘의 할인",
            color = Color.Black,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(item.size) { book ->
                BVerticalCard(
                    title = item[book].title,
                    thumbnail = item[book].image,
                    isDisCount = true,
                    discount = item[book].salePercent,
                    price = item[book].price,
                    salePrice = item[book].salePrice,
                    onClick = {  },
                )
            }
        }
    }
}