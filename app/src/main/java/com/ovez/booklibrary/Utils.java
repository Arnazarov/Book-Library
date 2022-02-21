package com.ovez.booklibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currReadingBooks;
    private static ArrayList<Book> favouriteBooks;

    public Utils() {
        if (allBooks == null) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (alreadyReadBooks == null)
            alreadyReadBooks = new ArrayList<>();

        if (wantToReadBooks == null)
            wantToReadBooks = new ArrayList<>();

        if (currReadingBooks == null)
            currReadingBooks = new ArrayList<>();

        if (favouriteBooks == null)
            favouriteBooks = new ArrayList<>();
    }

    private void initData() {
        //TODO: add initial data
        allBooks.add(new Book(1, "Sapiens", "Yuval N. Harari", 584, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1427068429l/23346740.jpg", "A Summer Reading Pick for President Barack Obama, Bill Gates, and Mark Zuckerberg", "From a renowned historian comes a groundbreaking narrative of humanity’s creation and evolution—a #1 international bestseller—that explores the ways in which biology and history have defined us and enhanced our understanding of what it means to be “human.”"));

        allBooks.add(new Book(2, "Homo Deus", "Yuval N. Harari", 632, "https://images-na.ssl-images-amazon.com/images/I/71PuIuX-64L.jpg", "A Summer Reading Pick for President Barack Obama, Bill Gates, and Mark Zuckerberg", "From a renowned historian comes a groundbreaking narrative of humanity’s creation and evolution—a #1 international bestseller—that explores the ways in which biology and history have defined us and enhanced our understanding of what it means to be “human.”"));
    }

    public static  Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }

    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrReadingBooks() {
        return currReadingBooks;
    }

    public static ArrayList<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public Book getBookById(int id) {
        for (Book book : allBooks) {
            if (book.getId() == id)
                return book;
        }

        return null;
    }
}
