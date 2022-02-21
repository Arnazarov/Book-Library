package com.ovez.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
        
        setData(book);



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