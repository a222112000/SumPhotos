package com.alselwi.sumimagesapi.ui.screen

sealed class Screen(val route: String){
    object Photos: Screen("photos")
    object FavoritePhotos: Screen("favorite_photos")

}