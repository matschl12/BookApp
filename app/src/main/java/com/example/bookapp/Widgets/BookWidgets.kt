package com.example.bookapp.Widgets

import BooksViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookapp.models.Book



@Composable
fun BookList (modifier: Modifier, onBookRemove: (Book) -> Unit, navController: NavController, booksViewModel: BooksViewModel){
    LazyColumn (modifier = modifier){
        itemsIndexed(booksViewModel.books) { index, book ->
            BookRow(book, index, onRemove = {onBookRemove(book)}, navController)
        }
    }
}

@Composable
fun BookRow (book: Book, index: Int, onRemove: () -> Unit, navController: NavController) {
    var showDetails by remember { mutableStateOf(false) }
    val booksViewModel = BooksViewModel()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable { showDetails = !showDetails }, //to show or hide details
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "\"" + book.title + "\" von " + book.author)
                Icon(
                    imageVector = if (showDetails) Icons.Filled.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                    contentDescription = "show more"
                )
            }

            AnimatedVisibility(
                visible = showDetails,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Released: ${book.release}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "On Readlist: ${book.onReadList}")
                    if(!book.onReadList)
                    {
                        Button(onClick = { booksViewModel.editReadList(book) }) {
                            Text(text = "Add to Readlist")
                        }
                    }
                    else
                    {
                        Button(onClick = { booksViewModel.editReadList(book) }) {
                            Text(text = "Remove from Readlist")
                        }
                    }
                    Button(onClick = {
                            navController.navigate("AddBooks/${index}/${book.title}/${book.author}/${book.release}/${book.isbn}/${book.onReadList}/true")
                            {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                    })
                    {

                        Text(text = "Edit")
                    }
                    Button(onClick = {
                        booksViewModel.deleteBook(book)
                        onRemove()
                         // Book(book)
                    }) {
                        Text(text = "Remove")
                    }
                }
            }
        }
    }
}