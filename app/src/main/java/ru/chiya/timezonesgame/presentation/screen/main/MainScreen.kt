package ru.chiya.timezonesgame.presentation.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.chiya.timezonesgame.R
import ru.chiya.timezonesgame.presentation.screen.common.GradientButton
import ru.chiya.timezonesgame.presentation.ui.theme.ButtonGradient

@Composable
fun MainScreen (
    navController: NavController
) {
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
        Column {// Top block
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = "bg image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(520.dp)
            )
            Text(
                text = "match the time zone with the city".uppercase(),
                style = TextStyle(
                    fontSize = 50.sp,
                    lineHeight = 50.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
        Column(Modifier.padding(bottom = 60.dp)) { // Bottom block
            GradientButton(text = "PLAY", gradient = ButtonGradient, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
            )
        }
    }
}