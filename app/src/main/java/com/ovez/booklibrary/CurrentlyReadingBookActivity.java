package com.ovez.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingBookActivity extends AppCompatActivity {
    public static final String CURRENTLY_READING_BOOK_ACTIVITY = "CurrentlyReadingBookActivity";
    private RecyclerView recyclerView;
    private BookRecViewAdapter adapter;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_book);

        recyclerView = findViewById(R.id.currentlyReadingBooksRecView);
        adapter = new BookRecViewAdapter(this, CURRENTLY_READING_BOOK_ACTIVITY);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setBooks(Utils.getCurrReadingBooks());
    }
}