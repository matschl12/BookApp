package com.example.bookapp.Screen

import BooksViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookapp.Widgets.BottomNavBar
import com.example.bookapp.BottomNavigationItem

@Composable
fun AddBooks(navController: NavController, index: Int, oldTitle: String, oldAuthor: String, oldRelease: Int, oldIsbn: String, onReadList: Boolean, isBeingEdited: Boolean, booksViewModel: BooksViewModel){
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
    var title by remember {
        mutableStateOf(oldTitle)
    }
    var author by remember {
        mutableStateOf(oldAuthor)
    }
    var release by remember {
        mutableStateOf(oldRelease.toString())
    }
    var isbn1 by remember {
        mutableStateOf(oldIsbn.split("-").getOrNull(0) ?: "")
    }
    var isbn2 by remember {
        mutableStateOf(oldIsbn.split("-").getOrNull(1) ?: "")
    }
    var isbn3 by remember {
        mutableStateOf(oldIsbn.split("-").getOrNull(2) ?: "")
    }
    var isbn4 by remember {
        mutableStateOf(oldIsbn.split("-").getOrNull(3) ?: "")
    }
    var isbn5 by remember {
        mutableStateOf(oldIsbn.split("-").getOrNull(4) ?: "")
    }
    var popUp by remember {
        mutableStateOf("")
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(items, navController)
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            Text(text = if (!isBeingEdited) "Add Book" else "Edit Book") //shows the correct text on top of the screen on the when a book is either getting added or edited
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                TextField(
                    value = title,
                    onValueChange = {title = it},
                    label = {Text("Title")}
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                TextField(
                    value = author,
                    onValueChange = {author = it},
                    label = {Text("Author")}
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                TextField(
                    value = release,
                    onValueChange = {release = it},
                    label = {Text("Released")}
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = isbn1,
                    onValueChange = { isbn1 = it },
                    label = { Text("ISBN (1)") },
                    modifier = Modifier.weight(1f) // arranges the space evenly
                )
                Text("-", modifier = Modifier.padding(horizontal = 4.dp))
                TextField(
                    value = isbn2,
                    onValueChange = { isbn2 = it },
                    label = { Text("ISBN (2)") },
                    modifier = Modifier.weight(1f)
                )
                Text("-", modifier = Modifier.padding(horizontal = 4.dp))
                TextField(
                    value = isbn3,
                    onValueChange = { isbn3 = it },
                    label = { Text("ISBN (3)") },
                    modifier = Modifier.weight(1f)
                )
                Text("-", modifier = Modifier.padding(horizontal = 4.dp))
                TextField(
                    value = isbn4,
                    onValueChange = { isbn4 = it },
                    label = { Text("ISBN (4)") },
                    modifier = Modifier.weight(1f)
                )
                Text("-", modifier = Modifier.padding(horizontal = 4.dp))
                TextField(
                    value = isbn5,
                    onValueChange = { isbn5 = it },
                    label = { Text("ISBN (5)") },
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button( onClick = {
                   popUp = booksViewModel.validateAndSaveBook(index,title, author, release, isbn1, isbn2, isbn3, isbn4, isbn5, isBeingEdited, onReadList, navController )
                }) {
                    Text(text = if (!isBeingEdited) "Add Book" else "Edit Book") //shows the correct text on the button on the when a book is either getting added or edited
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = popUp) //notices the user if a was added or edited. Also shows the user error messages.
            }
        }
    }
}
