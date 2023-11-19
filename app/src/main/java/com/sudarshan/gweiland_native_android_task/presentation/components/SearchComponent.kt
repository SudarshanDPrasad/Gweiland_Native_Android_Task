package com.sudarshan.gweiland_native_android_task.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudarshan.gweiland_native_android_task.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    onFilterClick: (Boolean) -> Unit,
) {
    var search by remember { mutableStateOf("") }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            value = search,
            onValueChange = { searchValue ->
                search = searchValue
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_cryptocurrency),
                    color = Color(0xFF0b0b0b).copy(alpha = 0.3f),
                    fontSize = 11.sp
                )
            },
            leadingIcon = {
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = ""
                )
            },
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFE6E6E6),
                focusedTextColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Row(
            modifier = Modifier
                .height(50.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) {
                    onFilterClick(true)
                }
                .background(
                    color = Color.Transparent,
                    shape = CircleShape
                )
                .border(width = 1.dp, color = Color.Black, shape = CircleShape)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(id = R.drawable.filter),
                contentDescription = ""
            )

            Text(
                text = stringResource(R.string.filter),
                color = Color(0xFF0b0b0b).copy(alpha = 0.3f),
            )
        }
    }
}