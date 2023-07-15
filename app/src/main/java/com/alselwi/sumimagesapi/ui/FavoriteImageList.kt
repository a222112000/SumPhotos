package com.alselwi.sumimagesapi.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.alselwi.sumimagesapi.data.model.SumPicturesItem
import com.alselwi.sumimagesapi.ui.bottom_bar.BottomBar
import com.alselwi.sumimagesapi.ui.top_bar.TopBar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private lateinit var arrayFavorite: ArrayList<SumPicturesItem>
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CommitPrefEdits")
@Composable
fun FavoriteImageList(navController: NavController,context: Context, viewModel: PicsViewModel = hiltViewModel()) {

    val sharedPrefs: SharedPreferences = context.getSharedPreferences("fav",Context.MODE_PRIVATE)

    val gson = Gson()
    val getJson = sharedPrefs.getString("favorites","[]")
    val favor = sharedPrefs.getString("url","")

    val type: Type = object: TypeToken<ArrayList<SumPicturesItem>>() {}.type
    if(getJson != null) {
        arrayFavorite = gson.fromJson(getJson, type)
    }
    Scaffold(topBar = {
        TopBar("Favorite")
    }, modifier = Modifier.fillMaxWidth(),
        bottomBar = {
            BottomBar(navController)
        },
    ) {
        LazyColumn {
            items(arrayFavorite){
                Column(modifier = Modifier.padding(top = 59.dp)) {
                    FavoriteImageItem(favoriteImage = it.download_url,it.id.toInt(),it.isFavor)
                }
            }
        }
    }
}

@Composable
fun FavoriteImageItem(favoriteImage: String,id: Int,isFavorite:Boolean) {
    Card(modifier = Modifier.padding(3.dp)) {
        Column() {
            Column(modifier = Modifier.fillMaxSize()) {
                Text("${id}", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp), fontSize = 24.sp ,textAlign = TextAlign.Center
                )

            }
            Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.padding(start = 185.dp)
                )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(300.dp), verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.padding(top = 8.dp),
                    painter = rememberImagePainter(favoriteImage),
                    contentDescription = "Image",
                )
            }
        }
    }
}