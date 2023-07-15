package com.alselwi.sumimagesapi.ui.top_bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String) {
    TopAppBar(
        title = {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Row() {

                    Text(
                        text = title,
                        modifier = Modifier.padding(top = 6.dp, end = 8.dp).fillMaxWidth(),
                        style = TextStyle(color = Color.Black, fontSize = 27.sp),
                        textAlign = TextAlign.Start,
                    )
                }
            }
        })
}