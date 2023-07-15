package com.alselwi.sumimagesapi.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.alselwi.sumimagesapi.data.model.SumPicturesItem
import com.alselwi.sumimagesapi.ui.bottom_bar.BottomBar
import com.alselwi.sumimagesapi.ui.top_bar.TopBar
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CommitPrefEdits")
@Composable
fun ImageSumScreen(navController: NavController,context: Context, viewModel: PicsViewModel = hiltViewModel()) {
    val state = viewModel.pics.value.photos
    //val url = viewModel.image.value
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("fav",Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    val gson: Gson = Gson()
    val arrayFav: ArrayList<SumPicturesItem> = ArrayList()

    Scaffold(topBar = {
        TopBar("Images")
    }, modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            BottomBar(navController)
        }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Image Sumx")

            LazyColumn {
                state?.let {pics->
                    items(pics){
                        ImageItem(image = it, onImageClick = {
                            it.isFavor = true
                            editor.putString("url",it.isFavor.toString())
                            arrayFav.add(
                                SumPicturesItem(
                                    "",
                                    it.download_url,
                                    0,
                                    it.id,
                                    "",
                                    0,
                                    false
                                )
                            )
                           // arrayFav.filter { it.isFavor }
                            val json: String = gson.toJson(arrayFav)

                            editor.putString("favorites",json)
                            editor.apply()

                        })
                    }
                }
            }

        }
    }
}

@Composable
fun ImageItem(image: SumPicturesItem, onImageClick: (SumPicturesItem) -> Unit) {
    Card(modifier = Modifier.padding(top = 38.dp).fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .size(350.dp)
            .clickable { onImageClick(image) }, verticalArrangement = Arrangement.Center
        ) {
            Text(
                image.id.toString(), modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), textAlign = TextAlign.Center
            )
            Image(
                modifier = Modifier.padding(top = 28.dp),
                painter = rememberImagePainter(image.download_url),
                contentDescription = "Image",
            )
        }
    }
}
