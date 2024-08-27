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
        startDestination="FavBooks"){
        composable(route="FavBooks"){
            FavBooks(navController)
        }


        composable("AddBooks/{index}/{oldTitel}/{oldAutor}/{oldRelease}/{oldIsbn}/{onReadList}/{isBeingEdited}")
        {
            backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
            val oldTitel = backStackEntry.arguments?.getString("oldTitel") ?: ""
            val oldAutor = backStackEntry.arguments?.getString("oldAutor") ?: ""
            val oldRelease = backStackEntry.arguments?.getString("oldRelease")?.toIntOrNull() ?: 0
            val oldIsbn = backStackEntry.arguments?.getString("oldIsbn") ?: ""
            val onReadList = backStackEntry.arguments?.getString("onReadList")?.toBoolean() ?: false
            val isBeingEdited = backStackEntry.arguments?.getString("isBeingEdited")?.toBoolean() ?: false


            AddBooks(
                navController,
                index = index,
                oldTitel = oldTitel,
                oldAutor = oldAutor,
                oldRelease = oldRelease,
                oldIsbn = oldIsbn,
                onReadList = onReadList,
                isBeingEdited = isBeingEdited
            )
        }
    }
}