package ru.chiya.timezonesgame.presentation.screen.casual

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.chiya.timezonesgame.R
import ru.chiya.timezonesgame.presentation.screen.common.GradientButton
import ru.chiya.timezonesgame.presentation.screen.common.TextField
import ru.chiya.timezonesgame.presentation.ui.theme.ButtonGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CasualScreen(
    casualViewModel: CasualViewModel = hiltViewModel(),
    navController: NavController
) {
    var score by remember {
        mutableStateOf(0)
    }
    val inputCity = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "bg image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp)
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text( //TODO: don't repeat yourself
                text = "Score: $score",
                style = TextStyle(
                    fontSize = 40.sp,
                    lineHeight = 40.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_medium)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                )
            )
            Image(
                painter = painterResource(id = casualViewModel.currentCity.image),
                contentDescription = casualViewModel.currentCity.city,
                modifier = Modifier
                    .width(213.dp)
                    .height(212.dp)
            )
            Column(
                Modifier.padding(bottom = 68.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(inputCity)
                Spacer(modifier = Modifier.height(15.dp))
                GradientButton(text = "OK",
                    gradient = ButtonGradient,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        score += if (casualViewModel.answer(inputCity.value)) 1 else -1
                        inputCity.value = ""
                        if (casualViewModel.questionsLeft <= 0) {
                            navController.navigate("result/$score")
                        }
                    }
                )
            }
        }
    }
}