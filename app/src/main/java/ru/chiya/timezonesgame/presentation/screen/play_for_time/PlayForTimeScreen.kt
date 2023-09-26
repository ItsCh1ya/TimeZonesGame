package ru.chiya.timezonesgame.presentation.screen.play_for_time

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ru.chiya.domain.model.Timezone
import ru.chiya.timezonesgame.R

data class Selected(
    var image: Int?, var city: String?
) {
    fun toTimezone(): Timezone? {
        return if (city != null && image != null) Timezone(city!!, image!!) else null
    }
}

@Composable
fun PlayForTimeScreen(
    playForTimeViewModel: PlayForTimeViewModel = hiltViewModel(), navController: NavHostController
) {
    val (height, width) = LocalConfiguration.current.run { screenHeightDp to screenWidthDp }
    val selected = Selected(null, null)
    var score by remember {
        mutableStateOf(0)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "bg image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp)
        )
        Box(
            Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            var expandedImage by remember { mutableStateOf<Int?>(null) }
            var expandedText by remember { mutableStateOf<String?>(null) }

            playForTimeViewModel.cities.forEach { city ->
                var imageVisible by remember { mutableStateOf(true) }
                val imagePosition by remember {
                    mutableStateOf(
                        Pair(
                            (50..width - 50).random().dp, (40..height / 2).random().dp
                        )
                    )
                }

                var textVisible by remember { mutableStateOf(true) }
                val textPosition by remember {
                    mutableStateOf(
                        Pair(
                            (40..width - 40).random().dp, (height / 2..height - 40).random().dp
                        )
                    )
                }

                // NOTE: To avoid overlaying we can remove used coordinates

                AnimatedVisibility(visible = imageVisible) {
                    Box(
                        Modifier
                            .offset(imagePosition.first, imagePosition.second)
                            .size(if (expandedImage == city.image) 150.dp else 100.dp)
                            .animateContentSize()
                            .clickable {
                                // Selecting image
                                // Select if not selected yet and replace selection if already selected
                                // Set selected to null if clicked same again
                                if (selected.image == null) {
                                    selected.image = city.image
                                    expandedImage = city.image
                                } else if (selected.image != city.image) {
                                    selected.image = city.image
                                    expandedImage = city.image
                                } else {
                                    selected.image = null
                                    expandedImage = null
                                }

                                if (playForTimeViewModel.answer(selected)) {
                                    imageVisible = false
                                    textVisible = false

                                    selected.city = null
                                    expandedText = null
                                    score++
                                }
                                // If selected image and city, but with incorrect answer
                                else if (selected.city != null && selected.image != null) {
                                    selected.city = null
                                    selected.image = null

                                    expandedText = null
                                    expandedImage = null
                                    score--
                                }
                                Log.d("score", score.toString())
                            }) {
                        Image(
                            painter = painterResource(id = city.image),
                            contentDescription = city.city,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                AnimatedVisibility(visible = textVisible) {
                    Box(
                        Modifier
                            .offset(textPosition.first, textPosition.second)
                            .size(if (expandedText == city.city) 150.dp else 100.dp)
                            .animateContentSize()
                            .clickable {
                                // Selecting city
                                // Select if not selected yet and replace selection if already selected
                                // Set selected to null if clicked same again
                                if (selected.city == null || selected.city != city.city) {
                                    selected.city = city.city
                                    expandedText = city.city
                                } else {
                                    selected.city = null
                                    expandedText = null
                                }

                                if (playForTimeViewModel.answer(selected)) {
                                    imageVisible = false
                                    textVisible = false
                                    selected.city = null
                                    expandedText = null
                                    score++
                                } else if (selected.city != null && selected.image != null) {
                                    selected.city = null
                                    selected.image = null

                                    expandedText = null
                                    expandedImage = null
                                    score--
                                }
                                Log.d("score", score.toString())
                            }) {
                        Text(
                            text = city.city,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            style = TextStyle(
                                fontSize = (if (expandedText == city.city) 26.sp else 16.sp),
                                fontFamily = FontFamily(Font(R.font.roboto_medium)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }
        }
    }
}