package com.ovez.booklibrary;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String WANT_TO_READ_BOOKS_KEY = "want_to_read_books";
    private static final String FAVOURITE_BOOKS_KEY = "favourite_books";
    private static Utils instance;
    private SharedPreferences sharedPreferences;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("db", Context.MODE_PRIVATE);

        if (getAllBooks() == null) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getWantToReadBooks() == null){
            editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getCurrReadingBooks() == null){
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getFavouriteBooks() == null){
            editor.putString(FAVOURITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Sapiens", "Yuval N. Harari", 584, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1427068429l/23346740.jpg", "A Summer Reading Pick for President Barack Obama, Bill Gates, and Mark Zuckerberg", "From a renowned historian comes a groundbreaking narrative of humanity’s creation and evolution—a #1 international bestseller—that explores the ways in which biology and history have defined us and enhanced our understanding of what it means to be “human.”"));
        books.add(new Book(2, "Homo Deus", "Yuval N. Harari", 632, "https://images-na.ssl-images-amazon.com/images/I/71PuIuX-64L.jpg", "A Summer Reading Pick for President Barack Obama, Bill Gates, and Mark Zuckerberg", "From a renowned historian comes a groundbreaking narrative of humanity’s creation and evolution—a #1 international bestseller—that explores the ways in which biology and history have defined us and enhanced our understanding of what it means to be “human.”"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();

    }

    public static Utils getInstance(Context context) {
        if (instance == null)
            instance = new Utils(context);
        return instance;
    }

    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;

    }

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> alreadyReadBooks = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null), type);
        return alreadyReadBooks;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> wantToReadBooks = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS_KEY, null), type);
        return wantToReadBooks;
    }

    public ArrayList<Book> getCurrReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> currentlyReadingBooks = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null), type);
        return currentlyReadingBooks;
    }

    public ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> favouriteBooks = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS_KEY, null), type);
        return favouriteBooks;
    }

    public Book getBookById(int id) {
        ArrayList<Book> allBooks = getAllBooks();
        if (allBooks != null) {
            for (Book book : allBooks) {
                if (book.getId() == id)
                    return book;
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = getAlreadyReadBooks();
        if (alreadyReadBooks != null) {
            if (alreadyReadBooks.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(ALREADY_READ_BOOKS_KEY);
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(alreadyReadBooks));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> wantToReadBooks = getWantToReadBooks();
        if (wantToReadBooks != null) {
            if (wantToReadBooks.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(WANT_TO_READ_BOOKS_KEY);
                editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(wantToReadBooks));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavouriteBooks(Book book) {
        ArrayList<Book> favouriteBooks = getFavouriteBooks();
        if (favouriteBooks != null) {
            if (favouriteBooks.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(FAVOURITE_BOOKS_KEY);
                editor.putString(FAVOURITE_BOOKS_KEY, gson.toJson(favouriteBooks));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book book) {
        ArrayList<Book> currReadingBooks = getCurrReadingBooks();
        if (currReadingBooks != null) {
            if (currReadingBooks.add(book)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(currReadingBooks));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean deleteFromAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = getAlreadyReadBooks();
        if (alreadyReadBooks != null) {
            for (Book b : alreadyReadBooks) {
                if (b.getId() == book.getId()) {
                    if (alreadyReadBooks.remove(b)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(ALREADY_READ_BOOKS_KEY);
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(alreadyReadBooks));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromCurrentlyReading(Book book) {
        ArrayList<Book> currReadingBooks = getCurrReadingBooks();
        if (currReadingBooks != null) {
            for (Book b : currReadingBooks) {
                if (b.getId() == book.getId()) {
                    if (currReadingBooks.remove(b)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(CURRENTLY_READING_BOOKS_KEY);
                        editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(currReadingBooks));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromWishlist(Book book) {
        ArrayList<Book> wantToReadBooks = getWantToReadBooks();
        if (wantToReadBooks != null) {
            for (Book b : wantToReadBooks) {
                if (b.getId() == book.getId()) {
                    if (wantToReadBooks.remove(b)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(WANT_TO_READ_BOOKS_KEY);
                        editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(wantToReadBooks));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean deleteFromFavourites(Book book) {
        ArrayList<Book> favouriteBooks = getFavouriteBooks();
        if (favouriteBooks != null) {
            for (Book b : favouriteBooks) {
                if (b.getId() == book.getId()) {
                    if (favouriteBooks.remove(b)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(FAVOURITE_BOOKS_KEY);
                        editor.putString(FAVOURITE_BOOKS_KEY, gson.toJson(favouriteBooks));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
