package com.sudarshan.gweiland_native_android_task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sudarshan.gweiland_native_android_task.R
import com.sudarshan.gweiland_native_android_task.data.response.model.cryptoList.Data
import com.sudarshan.gweiland_native_android_task.data.response.model.image.ImageResponseDTO

@Composable
fun CryptoCurrency(
    cryptoListData: List<Data>,
    imageListData: List<ImageResponseDTO>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(131.dp)
                .background(
                    color = Color(0xFF00ce08).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(20.dp)
                )
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.align(Alignment.BottomEnd),
                painter = painterResource(id = R.drawable.mask_group),
                contentDescription = ""
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 26.dp)
            ) {

                AsyncImage(
                    modifier = Modifier.size(46.dp),
                    model = imageListData.first().data.BTC.first().logo,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(15.dp))
                Column(
                    modifier = Modifier,

                    ) {
                    Text(
                        modifier = Modifier.width(37.dp),
                        text = cryptoListData.first().symbol,
                        color = Color(0xFF0B0B0B),
                        fontSize = 18.sp,
                        fontWeight = FontWeight(700),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.width(43.dp),
                        text = cryptoListData.first().name,
                        color = Color(0xFF0B0B0B),
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier,
                ) {
                    val value = cryptoListData.first().quote.USD.price.toString().split(".")
                    val priceValueOne = if (value[0].length < 3) {
                        "${value[0]}.${value[1].take(3)}"
                    } else if (value[0].length > 3 && value[0].length <= 5) {
                        "${value[0].take(2)},${value[0].takeLast(3)}"
                    } else {
                        "${value[0]}.${value[1].take(3)}"
                    }
                    Text(
                        modifier = Modifier.width(100.dp),
                        text = "$$priceValueOne USD",
                        color = Color(0xFF0B0B0B),
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.End)
                            .width(43.dp),
                        text = "${
                            cryptoListData.first().quote.USD.volume_change_24h.toString().take(5)
                        }%",
                        color = if (cryptoListData.first().quote.USD.volume_change_24h.toString()
                                .contains("-")
                        ) {
                            Color(0xFFFF3D00)
                        } else {
                            Color(0xFF00CE08)
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight(500),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.top_cryptocurrencies),
                color = Color(0xFF0B0B0B),
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier,
                text = stringResource(R.string.view_all),
                color = Color(0xFF0B0B0B).copy(alpha = 0.5f),
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        LazyColumn {
            items(cryptoListData) { response ->
                IndividualData(
                    symbol = response.symbol,
                    name = response.name,
                    price = response.quote.USD.price,
                    volume24h = response.quote.USD.volume_change_24h
                )
            }
        }
    }
}