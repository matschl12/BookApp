package com.example.bookapp.Widgets

import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bookapp.BottomNavigationItem
import com.example.bookapp.models.bookList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(
    items: List<BottomNavigationItem>,
    navController: NavController
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val selectedItemIndex = items.indexOfFirst { it.title == currentDestination?.route }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    if (selectedItemIndex == index) {
                        navController.popBackStack()
                    } else {
                        if (item.title == "AddBooks"){
                            navController.navigate("AddBooks/${bookList.size}/ / / /0-0-0-0-0/false") {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                        else{
                            navController.navigate(item.title) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }


                    }
                },
                label = { Text(text = item.title) },
                icon = {
                    BadgedBox(badge = { }) {
                        Icon(
                            imageVector = if (selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    }
                }
            )
        }
    }
}