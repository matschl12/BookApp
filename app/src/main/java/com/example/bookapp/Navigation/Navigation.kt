package com.example.bookapp.Navigation

import FavBooks
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.Screen.AddBooks

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination="FavBooks")
    {

        composable(route="FavBooks"){
            FavBooks(navController)
        }


        composable("AddBooks/{index}/{oldTitel}/{oldAutor}/{oldRelease}/{oldIsbn}/{onReadList}/{isBeingEdited}")
        {
            backStackEntry ->
            AddBooks(
                navController,
                backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0,
                backStackEntry.arguments?.getString("oldTitel") ?: "",
                backStackEntry.arguments?.getString("oldAutor") ?: "",
                backStackEntry.arguments?.getString("oldRelease")?.toIntOrNull() ?: 0,
                backStackEntry.arguments?.getString("oldIsbn") ?: "",
                backStackEntry.arguments?.getString("onReadList")?.toBoolean() ?: false,
                backStackEntry.arguments?.getString("isBeingEdited")?.toBoolean() ?: true
            )
        }
    }
}