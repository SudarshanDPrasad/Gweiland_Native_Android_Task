package com.sudarshan.gweiland_native_android_task.presentation.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudarshan.gweiland_native_android_task.R
import com.sudarshan.gweiland_native_android_task.presentation.viewModel.MainViewModel
import com.sudarshan.gweiland_native_android_task.ui.theme.Gweiland_Native_Android_TaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gweiland_Native_Android_TaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    if (viewModel.cryptoResponse.isNotEmpty() && viewModel.cryptoImage.isNotEmpty()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            MainScreen(
                                viewModel = viewModel,
                                filtered = { value ->
                                    when (value) {
                                        "price" -> {
                                            viewModel.cryptoResponse.sortBy {
                                                it.quote.USD.price
                                            }
                                        }

                                        "volume" -> {
                                            viewModel.cryptoResponse.sortBy {
                                                it.quote.USD.volume_change_24h
                                            }
                                        }

                                        "clear" -> {
                                            viewModel.cryptoResponse.sortBy {
                                                it.cmc_rank
                                            }
                                        }
                                    }
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .padding(25.dp)
                                    .fillMaxWidth()
                                    .height(71.dp)
                                    .background(
                                        color = Color(0xFF0B0B0B),
                                        shape = RoundedCornerShape(25.dp)
                                    )
                                    .align(Alignment.BottomEnd),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                BottomNavigation(
                                    modifier = Modifier.size(19.dp),
                                    name = "E-shop",
                                    image = R.drawable.e_shop,
                                    selected = false
                                )
                                BottomNavigation(
                                    modifier = Modifier.size(19.dp),
                                    name = "Exchange",
                                    image = R.drawable.exchange,
                                    selected = true
                                )
                                BottomNavigation(
                                    modifier = Modifier.size(54.dp),
                                    image = R.drawable.metaverse_1,
                                    selected = false
                                )
                                BottomNavigation(
                                    modifier = Modifier.size(19.dp),
                                    name = "Launchpads",
                                    image = R.drawable.launchpads,
                                    selected = false
                                )
                                BottomNavigation(
                                    modifier = Modifier.size(19.dp),
                                    name = "wallet",
                                    image = R.drawable.wallet,
                                    selected = false
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(
    modifier: Modifier,
    name: String? = null,
    image: Int,
    selected : Boolean
) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(id = image),
            contentDescription = ""
        )
        if (name != null) {
            Text(
                fontSize = 10.sp,
                text = name,
                color = if (selected) {
                    Color.White
                } else {
                    Color.White.copy(alpha = 0.40f)
                }
            )
        }
    }
}