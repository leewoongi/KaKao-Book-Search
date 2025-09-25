package com.woon.detail.ui.screen

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.description.BDescription
import com.woon.core.design.textview.BTextView

@Composable
fun FooterScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ){
        BTextView(
            text = "책소개",
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 3,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(2.dp))

        BTextView(
            text ="이 책은 코틀린 언어의 기초부터 고급 기능까지 체계적으로 다루는 완벽한 가이드입니다. 안드로이드 개발에 필요한 모든 코틀린 지식을 실무 예제와 함께 학습할 수 있으며, 코루틴, 플로우 등 최신 기능들도 상세히 설명합니다...\n",
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = Color(0xFF6B7280),
        )

        Spacer(modifier = Modifier.height(24.dp))

        BTextView(
            text = "상세 정보",
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 3,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(2.dp))

        BDescription(
            modifier = Modifier.fillMaxWidth(),
            index = "저자",
            description = "윤성우"
        )

        Spacer(modifier = Modifier.height(2.dp))

        BDescription(
            modifier = Modifier.fillMaxWidth(),
            index = "저자",
            description = "윤성우"
        )

        Spacer(modifier = Modifier.height(2.dp))

        BDescription(
            modifier = Modifier.fillMaxWidth(),
            index = "저자",
            description = "윤성우"
        )

        Spacer(modifier = Modifier.height(2.dp))

        BDescription(
            modifier = Modifier.fillMaxWidth(),
            index = "저자",
            description = "윤성우"
        )
    }
}