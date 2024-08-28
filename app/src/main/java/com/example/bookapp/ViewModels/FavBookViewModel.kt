import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.bookapp.models.Book
import com.example.bookapp.models.bookList
import com.example.bookapp.models.getBooks
import com.example.bookapp.models.isbnChecker

class FavBooksViewModel : ViewModel() {
    private val _books = getBooks().toMutableStateList()
    val books: List<Book>
        get() = _books

    fun deleteBook(book: Book) {
        _books.remove(book)
        bookList.remove(book)// books.value = getBooks()
    }

    fun addBook(book: Book){
        _books.add(book)
        bookList.add(book)
    }

    fun editReadList(book: Book){
        if(!book.onReadList)
        {
            book.onReadList = true
        }
        else
        {
            book.onReadList = false
        }
    }

    fun editBook(index: Int, titel: String, autor: String, release: Int, isbn: String, onReadList: Boolean){
        _books.removeAt(index)
        _books.add(index,  Book(titel, autor, release, isbn, onReadList))

        bookList.removeAt(index)
        bookList.add(index,  Book(titel, autor, release, isbn, onReadList))


    }

    fun validateAndSaveBook(index: Int, title: String, author: String, release: String, isbn1: String, isbn2: String, isbn3: String, isbn4: String, isbn5: String, isBeingEdited: Boolean, onReadList: Boolean, navController: NavController): String
    {
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
                                    if (!isBeingEdited)
                                    {
                                        addBook(Book(title, author, release.toInt(), completeISBN, onReadList))
                                        return "Book added"
                                        // navController.popBackStack()
                                    }
                                    else
                                    {
                                        editBook(index, title, author, release.toInt(), completeISBN, onReadList)
                                        navController.popBackStack()
                                        return "Book edited"
                                    }

                                }
                                else {
                                    return "Invalid Input. The ISBN is not a valid ISBN."
                                }

                            } else {
                                return "Invalid Input. The 'ISBN' field must only contain digits."
                            }
                        } else {
                            return "Invalid Input. The last ISBN field must contain only one digit."
                        }
                    } else {
                        return "Invalid Input. The 'ISBN' field must contain exactly 13 digits."
                    }
                } else {
                    return "Invalid Input. The 'Release' field must contain a number between 0 and 2024."
                }
            } else {
                return "Invalid Input. The 'Release' field must contain a valid number."
            }
        } else {
            return "Invalid Input. Every TextField must be filled out."
        }
    }

}
