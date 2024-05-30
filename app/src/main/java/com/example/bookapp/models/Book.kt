package com.example.bookapp.models

data class Book (
    val titel: String,
    val autor: String,
    val release: Int,
    val isbn: String
)
    fun getBooks(): List<Book> {
        return listOf(
            Book(titel = "Die Tribute von Panem 1. Tödliche Spiele",
                autor = "Suzanne Collins",
                release = 2020,
                isbn = "978-3-7891-2127-2"),
            Book(titel = "Harry Potter und der Halbblutprinz",
                autor = "J. K. Rowling",
                release = 2018,
                isbn = "978-3-551-55746-9"),
            Book(titel = "1984",
                autor = "George Orwell",
                release = 2021,
                isbn = "978-3-7306-0976-7"),
            Book(titel = "Wir Kinder vom Bahnhof Zoo",
                autor = "Kai Hermann",
                release = 2017,
                isbn = "978-3-551-31732-2")

        )
    }
