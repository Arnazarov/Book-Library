package com.ovez.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    private TextView lbl_bookName, lbl_pages, lbl_author, tv_shortDesc, tv_longDesc, tv_bookName, tv_author, tv_pages;
    private Button btn_addToCurrentlyReading, btn_addToWantToRead, btn_addToAlreadyRead, btn_addToFavourite;
    private ImageView img_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        tv_author = findViewById(R.id.tv_author);
        tv_bookName = findViewById(R.id.tv_book);
        tv_pages = findViewById(R.id.tv_pages);
        tv_shortDesc = findViewById(R.id.label_shortDesc);
        tv_longDesc = findViewById(R.id.tv_longDesc);

        btn_addToAlreadyRead = findViewById(R.id.btn_addToAlreadyRead);
        btn_addToCurrentlyReading = findViewById(R.id.btn_addToCurrentlyreading);
        btn_addToFavourite = findViewById(R.id.btn_addToFavourite);
        btn_addToWantToRead = findViewById(R.id.btn_addToWantToRead);

        img_book = findViewById(R.id.img_bookSingle);

        //TODO: Get data from recycler view in here
        Book book = new Book(1, "Sapiens", "Yuval N. Harari", 584, "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1427068429l/23346740.jpg", "A Summer Reading Pick for President Barack Obama, Bill Gates, and Mark Zuckerberg", "From a renowned historian comes a groundbreaking narrative of humanity’s creation and evolution—a #1 international bestseller—that explores the ways in which biology and history have defined us and enhanced our understanding of what it means to be “human.”");

        Intent intent = getIntent();

        if (intent != null) {
            int bookId = intent.getIntExtra("bookId", -1);
            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (incomingBook != null)
                    setData(incomingBook);

                handleAlreadyRead(incomingBook);
                handleWantToRead(incomingBook);
                handleCurrentlyReading(incomingBook);
                handleFavourites(incomingBook);
            }
        }

    }

    private void handleFavourites(Book incomingBook) {
    }

    private void handleCurrentlyReading(Book incomingBook) {
    }

    private void handleWantToRead(Book incomingBook) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean exists = false;

        for (Book book : wantToReadBooks) {
            if (book.getId() == incomingBook.getId())
                exists = true;
        }

        if (exists)
            btn_addToWantToRead.setEnabled(false);
        else {
            btn_addToWantToRead.setOnClickListener(view -> {
                if (Utils.getInstance().addToWantToRead(incomingBook)) {
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * Enable and Disable button,
     * Add the book to Already Read Book ArrayList
     *
     * @param incomingBook
     */
    private void handleAlreadyRead(Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean exists = false;

        for (Book book : alreadyReadBooks) {
            if (book.getId() == incomingBook.getId())
                exists = true;
        }

        if (exists)
            btn_addToAlreadyRead.setEnabled(false);
        else {
            btn_addToAlreadyRead.setOnClickListener(view -> {
                if (Utils.getInstance().addToAlreadyRead(incomingBook)) {
                    Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                    startActivity(intent);
                }
            });
        }


    }

    private void setData(Book book) {
        tv_bookName.setText(book.getName());
        tv_author.setText(book.getAuthor());
        tv_pages.setText(String.valueOf(book.getPages()));
        tv_shortDesc.setText(book.getShortDesc());
        tv_longDesc.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap()
                .load(book.getImageURL())
                .into(img_book);

    }
}