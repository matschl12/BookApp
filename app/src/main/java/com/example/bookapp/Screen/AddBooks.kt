package com.example.bookapp.Screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bookapp.BottomNavigationItem
import com.example.bookapp.Greeting

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddBooks(navController: NavController){
    val items = listOf(
        BottomNavigationItem(
            title = "FavBooks",
            selectedIcon = Icons.Filled.Menu,
            unselectedIcon = Icons.Outlined.Menu
        ),
        BottomNavigationItem(
            title = "AddBooks",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add
        )
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(1)
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                items.forEachIndexed{index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.title)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {

                                }
                            ){
                                Icon(
                                    imageVector = if(index == selectedItemIndex)
                                    {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            } })
                }
            }
        }
    ) { innerPadding ->
        Greeting(
            name = "Add Books",
            modifier = Modifier.padding(innerPadding)
        )
    }
}