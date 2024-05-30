import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bookapp.Widgets.BottomNavBar
import com.example.bookapp.Widgets.BookList
import com.example.bookapp.BottomNavigationItem
import com.example.bookapp.models.getBooks


@Composable
fun FavBooks(navController: NavController){
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

        if (getBooks().size == 0){
            Text(
                text = "Es wurden noch keine Bücher angelegt",
                modifier = Modifier.padding(innerPadding)
            )
        }
        else
        {
            BookList(
                modifier = Modifier.padding(innerPadding),
                books = getBooks()
            )
        }
    }
}


