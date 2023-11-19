package com.sudarshan.gweiland_native_android_task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudarshan.gweiland_native_android_task.R

@Composable
fun IndividualData(
    symbol: String,
    name: String,
    price: Double,
    volume24h: Double,
) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Image(
            modifier = Modifier.size(46.dp),
            painter = painterResource(id = R.drawable.dummy),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(15.dp))
        Column(
            modifier = Modifier.weight(0.5f),
        ) {
            Text(
                modifier = Modifier,
                text = symbol,
                color = Color(0xFF0B0B0B),
                fontSize = 18.sp,
                fontWeight = FontWeight(600),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier,
                text = name,
                color = Color(0xFF0B0B0B),
                fontSize = 13.sp,
                fontWeight = FontWeight(500),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        Image(
            modifier = Modifier.size(46.dp),
            painter = if (volume24h.toString().contains("-")) {
                painterResource(id = R.drawable.drop_down_grap)
            } else {
                painterResource(id = R.drawable.up_graph)
            },
            contentDescription = ""
        )
        Spacer(modifier = Modifier.weight(0.3f))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            val value = price.toString().split(".")
            val priceValueOne = if (value[0].length < 3) {
                "${value[0]}.${value[1].take(3)}"
            } else if (value[0].length > 3 && value[0].length <= 5) {
                "${value[0].take(2)},${value[0].takeLast(3)}"
            } else {
                "${value[0]}.${value[1].take(3)}"
            }
            Text(
                modifier = Modifier.align(Alignment.End),
                text = "$$priceValueOne USD",
                color = Color(0xFF0B0B0B),
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .align(Alignment.End),
                text = "${volume24h.toString().take(5)}%",
                color = if (volume24h.toString().contains("-")) {
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
