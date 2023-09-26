package ru.chiya.timezonesgame.presentation.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.chiya.timezonesgame.R

@Composable
fun TextField(text: MutableState<String>) {
    BasicTextField(
        value = text.value,
        onValueChange = { text.value = it },

        ) {
        Row(
            Modifier
                .width(388.dp)
                .height(65.dp)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 50.dp)),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text.value.ifEmpty { "укажи ответ".uppercase() },
                style = TextStyle(
                    fontSize = 30.sp,
                    lineHeight = 30.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFDCDCDC),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}