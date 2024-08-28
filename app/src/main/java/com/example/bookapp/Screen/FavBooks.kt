import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookapp.Widgets.BottomNavBar
import com.example.bookapp.Widgets.BookList
import com.example.bookapp.BottomNavigationItem
import com.example.bookapp.models.getBooks
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun FavBooks(navController: NavController, favBooksViewModel: FavBooksViewModel){
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(items, navController)
        }
    ) { innerPadding ->
        Spacer(modifier = Modifier.height(35.dp))

        if (favBooksViewModel.books.isEmpty()){
            Text(
                text = "Es wurden noch keine Bücher angelegt",
                modifier = Modifier.padding(innerPadding)
            )
        }
        else
        {
            BookList(
                modifier = Modifier.padding(innerPadding),
                onBookRemove = {book -> favBooksViewModel.deleteBook(book)},
                navController = navController,
                favBooksViewModel = favBooksViewModel
            )
        }
    }
}


