package com.example.bookapp.Widgets

import FavBooksViewModel
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
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookapp.models.Book



@Composable
fun BookList (modifier: Modifier, onBookRemove: (Book) -> Unit, navController: NavController, favBooksViewModel: FavBooksViewModel){
    LazyColumn (modifier = modifier){
        itemsIndexed(favBooksViewModel.books) {index, book ->
            BookRow(book, index, onRemove = {onBookRemove(book)}, navController)
        }
    }
}

@Composable
fun BookRow (book: Book, index: Int, onRemove: () -> Unit, navController: NavController) {
    var showDetails by remember { mutableStateOf(false) }
    val favBooksViewModel = FavBooksViewModel()

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
                    .clickable { showDetails = !showDetails }, // Zum Ein- und Ausklappen
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "\"" + book.titel + "\" von " + book.autor)
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
                        Button(onClick = { favBooksViewModel.editReadList(book) }) {
                            Text(text = "Add to Readlist")
                        }
                    }
                    else
                    {
                        Button(onClick = { favBooksViewModel.editReadList(book) }) {
                            Text(text = "Remove from Readlist")
                        }
                    }
                    Button(onClick = {
                            navController.navigate("AddBooks/${index}/${book.titel}/${book.autor}/${book.release}/${book.isbn}/${book.onReadList}/true")
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
                        favBooksViewModel.deleteBook(book)
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