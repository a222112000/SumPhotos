package com.alselwi.sumimagesapi.ui.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alselwi.sumimagesapi.ui.screen.Screen

@Composable
fun BottomBar(navController: NavController = rememberNavController()){
    var nav by remember {
        mutableStateOf("Home")
    }
    BottomAppBar() {
        NavigationBarItem(selected = nav == "Photos"
            , onClick = {
                navController.navigate(Screen.Photos.route)
            },
            label = { Text(text = "Photos") },
            icon = { Icon(imageVector = Icons.Default.Home,
                contentDescription = null) }
        )
        NavigationBarItem(selected = nav == "Favorite"
            , onClick = {
                navController.navigate(Screen.FavoritePhotos.route)
            },
            label = { Text(text = "Favorite") },
            icon = { Icon(imageVector = Icons.Default.Favorite,
                contentDescription = null) }
        )
    }
}