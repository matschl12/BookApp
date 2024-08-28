package com.example.bookapp.models

data class Book (
    var titel: String,
    var autor: String,
    var release: Int,
    var isbn: String,
    var onReadList: Boolean
)

var bookList = mutableListOf(
        Book(titel = "Die Tribute von Panem 1. Tödliche Spiele",
            autor = "Suzanne Collins",
            release = 2020,
            isbn = "978-3-7891-2127-2",
            onReadList = false),

        Book(titel = "Harry Potter und der Halbblutprinz",
            autor = "J. K. Rowling",
            release = 2018,
            isbn = "978-3-551-55746-9",
            onReadList = false),
        Book(titel = "1984",
            autor = "George Orwell",
            release = 2021,
            isbn = "978-3-7306-0976-7",
            onReadList = false),
        Book(titel = "Wir Kinder vom Bahnhof Zoo",
            autor = "Kai Hermann",
            release = 2017,
            isbn = "978-3-551-31732-2",
            onReadList = false),
        Book(titel = "Tschick",
            autor = "Wolfgang Herrndorf",
            release = 2013,
            isbn = "978-3-944668-03-1",
            onReadList = false)
)
fun getBooks(): List<Book> {
    return bookList
}

fun isbnChecker(isbn: Long): Boolean{
    val firstDigit = isbn.toString().first().toString().toInt()
    val secondDigit = isbn.toString()[1].toString().toInt()
    val thirdDigit = isbn.toString()[2].toString().toInt()
    val fourthDigit = isbn.toString()[3].toString().toInt()
    val fifthDigit = isbn.toString()[4].toString().toInt()
    val sixthDigit = isbn.toString()[5].toString().toInt()
    val seventhDigit = isbn.toString()[6].toString().toInt()
    val eighthDigit = isbn.toString()[7].toString().toInt()
    val ninthDigit = isbn.toString()[8].toString().toInt()
    val tenthDigit = isbn.toString()[9].toString().toInt()
    val eleventhDigit = isbn.toString()[10].toString().toInt()
    val twelveDigit = isbn.toString()[11].toString().toInt()
    val checkSum = isbn.toString()[12].toString().toInt()

    val isbnCalc = (firstDigit * 1 + secondDigit * 3 + thirdDigit * 1 + fourthDigit * 3 + fifthDigit * 1 + sixthDigit * 3 + seventhDigit * 1 + eighthDigit * 3 + ninthDigit * 1 + tenthDigit * 3 + eleventhDigit * 1 + twelveDigit * 3) % 10

    if (isbnCalc == 0 && checkSum == 0)
    {
        return true
    }

    if (10 - isbnCalc == checkSum)
    {
        return true
    }

    return false
}
