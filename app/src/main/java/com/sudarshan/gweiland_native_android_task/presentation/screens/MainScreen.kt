package com.sudarshan.gweiland_native_android_task.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sudarshan.gweiland_native_android_task.R
import com.sudarshan.gweiland_native_android_task.presentation.components.CryptoCurrency
import com.sudarshan.gweiland_native_android_task.presentation.components.NFT
import com.sudarshan.gweiland_native_android_task.presentation.components.SearchComponent
import com.sudarshan.gweiland_native_android_task.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    filtered: (String) -> Unit
) {
    var selectedCurrency by remember { mutableStateOf(0) }
    var filterClicked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 26.dp, end = 26.dp, top = 26.dp, bottom = 25.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.exchanges),
                color = Color(0xFF0B0B0B),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
            )
            Spacer(modifier = Modifier.weight(0.7f))
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.notification),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.settings),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.size(25.dp))
        SearchComponent(
            onFilterClick = {
                if (it) {
                    filterClicked = true
                } else {
                    filterClicked = false
                    filtered("clear")
                }
            }
        )
        Spacer(modifier = Modifier.size(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.clickable {
                    selectedCurrency = 0
                },
                text = stringResource(R.string.cryptocurrency),
                color = if (selectedCurrency == 0) Color(0xFF0B0B0B) else Color(0xFF0B0B0B).copy(
                    alpha = 0.3f
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
            )
            Spacer(modifier = Modifier.size(25.dp))
            Text(
                modifier = Modifier.clickable {
                    selectedCurrency = 1
                },
                text = stringResource(R.string.nft),
                color = if (selectedCurrency == 1) Color(0xFF0B0B0B) else Color(0xFF0B0B0B).copy(
                    alpha = 0.3f
                ),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        when (selectedCurrency) {
            0 -> CryptoCurrency(
                cryptoListData = viewModel.cryptoResponse,
                imageListData = viewModel.cryptoImage
            )

            1 -> NFT()
        }
    }

    if (filterClicked) {
        Dialog(onDismissRequest = {
            filterClicked = false
            filtered("")
        }) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .padding(26.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.clickable {
                        filterClicked = false
                        filtered("price")
                    },
                    text = "Filter By price "
                )
                Spacer(modifier = Modifier.size(15.dp))
                Text(
                    modifier = Modifier.clickable {
                        filterClicked = false
                        filtered("volume")
                    },
                    text = "Filter By volume_24h"
                )
            }
        }
    }
}
