package com.example.bookapp.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookapp.Widgets.BottomNavBar
import com.example.bookapp.BottomNavigationItem
import com.example.bookapp.models.Book
import com.example.bookapp.models.addBook
import com.example.bookapp.models.editBook
import com.example.bookapp.models.getBooks
import com.example.bookapp.models.isbnChecker

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
    var title by remember {
        mutableStateOf("")
    }
    var author by remember {
        mutableStateOf("")
    }
    var release by remember {
        mutableStateOf("")
    }
    var isbn1 by remember {
        mutableStateOf("")
    }
    var isbn2 by remember {
        mutableStateOf("")
    }
    var isbn3 by remember {
        mutableStateOf("")
    }
    var isbn4 by remember {
        mutableStateOf("")
    }
    var isbn5 by remember {
        mutableStateOf("")
    }
    var error by remember {
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
            Text(text = "ADD Books")
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
                    modifier = Modifier.weight(1f) // verteilt den Platz gleichmäßig
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
                    if (title.isNotBlank() && author.isNotBlank() && release.isNotBlank() && isbn1.isNotBlank() && isbn2.isNotBlank() && isbn3.isNotBlank() && isbn4.isNotBlank() && isbn5.isNotBlank()) { //checks if every TextField is filled out
                        val releaseYear = release.toIntOrNull() //converts "release"-input into a number (int), when not possible into Null
                        if (releaseYear != null) { //checks if the converted value is a number
                            if (releaseYear in 0..2024) { //checks if release year value is between 0 and 2024
                                if (isbn1.length + isbn2.length + isbn3.length + isbn4.length + isbn5.length == 13) { //checks if isbn input is exactly 13 digits long
                                    if (isbn5.length == 1) {
                                        val isbn1Check = isbn1.toLongOrNull() //converts "isbn"-input into a number (long) if isbn only contains numbers
                                        val isbn2Check = isbn2.toLongOrNull()
                                        val isbn3Check = isbn3.toLongOrNull()
                                        val isbn4Check = isbn4.toLongOrNull()
                                        val isbn5Check = isbn5.toLongOrNull()
                                        if (isbn1Check != null && isbn2Check != null && isbn3Check != null && isbn4Check != null && isbn5Check != null) {
                                            val isbnForCalc = isbn1 + isbn2 + isbn3 + isbn4 + isbn5
                                            if(isbnChecker(isbnForCalc.toLong()))
                                            {
                                                val completeISBN = isbn1 + '-' + isbn2 + '-' + isbn3 + '-' + isbn4 + '-' + isbn5 //put all the digits from the isbn TextField into  valid isbn format
                                                addBook(Book(title, author, release.toInt(), completeISBN, onReadList = false))
                                                title = ""
                                                author = ""
                                                release = ""
                                                isbn1 = ""
                                                isbn2 = ""
                                                isbn3 = ""
                                                isbn4 = ""
                                                isbn5 = ""
                                                error = ""
                                            }
                                            else {
                                                error = "Invalid Input. The ISBN is not a valid ISBN."
                                            }

                                        } else {
                                            error = "Invalid Input. The 'ISBN' field must only contain digits."
                                        }
                                    } else {
                                        error = "Invalid Input. The last ISBN field must contain only one digit."
                                    }
                                } else {
                                    error = "Invalid Input. The 'ISBN' field must contain exactly 13 digits."
                                }
                            } else {
                                error = "Invalid Input. The 'Release' field must contain a number between 0 and 2024."
                            }
                        } else {
                            error = "Invalid Input. The 'Release' field must contain a valid number."
                        }
                    } else {
                        error = "Invalid Input. Every TextField must be filled out."
                    }
                }) {
                    Text("Add Book")
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = error)
            }
        }
    }
}

@Composable
fun AddBooks(navController: NavController, index: Int, oldTitel: String, oldAutor: String, oldRelease: Int, oldIsbn: String, onReadList: Boolean){
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
        mutableStateOf(oldTitel)
    }
    var author by remember {
        mutableStateOf(oldAutor)
    }
    var release by remember {
        mutableStateOf(oldRelease.toString())
    }
    var isbn1 by remember {
        mutableStateOf(oldIsbn.split("-").first())
    }
    var isbn2 by remember {
        mutableStateOf(oldIsbn.split("-")[1])
    }
    var isbn3 by remember {
        mutableStateOf(oldIsbn.split("-")[2])
    }
    var isbn4 by remember {
        mutableStateOf(oldIsbn.split("-")[3])
    }
    var isbn5 by remember {
        mutableStateOf(oldIsbn.split("-")[4])
    }
    var error by remember {
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
            Text(text = "EDIT Book NUMBER " +index)
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
                    modifier = Modifier.weight(1f) // verteilt den Platz gleichmäßig
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
                    if (title.isNotBlank() && author.isNotBlank() && release.isNotBlank() && isbn1.isNotBlank() && isbn2.isNotBlank() && isbn3.isNotBlank() && isbn4.isNotBlank() && isbn5.isNotBlank()) { //checks if every TextField is filled out
                        val releaseYear = release.toIntOrNull() //converts "release"-input into a number (int), when not possible into Null
                        if (releaseYear != null) { //checks if the converted value is a number
                            if (releaseYear in 0..2024) { //checks if release year value is between 0 and 2024
                                if (isbn1.length + isbn2.length + isbn3.length + isbn4.length + isbn5.length == 13) { //checks if isbn input is exactly 13 digits long
                                    if (isbn5.length == 1) {
                                        val isbn1Check = isbn1.toLongOrNull() //converts "isbn"-input into a number (long) if isbn only contains numbers
                                        val isbn2Check = isbn2.toLongOrNull()
                                        val isbn3Check = isbn3.toLongOrNull()
                                        val isbn4Check = isbn4.toLongOrNull()
                                        val isbn5Check = isbn5.toLongOrNull()
                                        if (isbn1Check != null && isbn2Check != null && isbn3Check != null && isbn4Check != null && isbn5Check != null) {
                                            val isbnForCalc = isbn1 + isbn2 + isbn3 + isbn4 + isbn5
                                            if(isbnChecker(isbnForCalc.toLong()))
                                            {
                                                val completeISBN = isbn1 + '-' + isbn2 + '-' + isbn3 + '-' + isbn4 + '-' + isbn5 //put all the digits from the isbn TextField into  valid isbn format
                                                editBook(index, title, author, release.toInt(), completeISBN, onReadList)
                                                navController.popBackStack()
                                                title = ""
                                                author = ""
                                                release = ""
                                                isbn1 = ""
                                                isbn2 = ""
                                                isbn3 = ""
                                                isbn4 = ""
                                                isbn5 = ""
                                                error = ""
                                            }
                                            else {
                                                error = "Invalid Input. The ISBN is not a valid ISBN."
                                            }

                                        } else {
                                            error = "Invalid Input. The 'ISBN' field must only contain digits."
                                        }
                                    } else {
                                        error = "Invalid Input. The last ISBN field must contain only one digit."
                                    }
                                } else {
                                    error = "Invalid Input. The 'ISBN' field must contain exactly 13 digits."
                                }
                            } else {
                                error = "Invalid Input. The 'Release' field must contain a number between 0 and 2024."
                            }
                        } else {
                            error = "Invalid Input. The 'Release' field must contain a valid number."
                        }
                    } else {
                        error = "Invalid Input. Every TextField must be filled out."
                    }
                }) {
                    Text("Edit Book")
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = error)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = getBooks().toString())
            }
        }
    }
}


@Preview
@Composable
fun AddBooksPreview()
{

}
