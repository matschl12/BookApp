package com.example.bookapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.Screen.AddBooks
import com.example.bookapp.Screen.FavBooks

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination="FavBooks"){
        composable(route="FavBooks"){
            FavBooks(navController)
        }

        composable("AddBooks"){
            AddBooks(navController)
        }
    }
}