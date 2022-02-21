package com.ovez.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_allBooks, btn_alreadyRead, btn_wantToRead, btn_currentlyReading, btn_favourite, btn_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btn_allBooks.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AllBooksActivity.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        btn_allBooks = findViewById(R.id.btn_allBooks);
        btn_alreadyRead = findViewById(R.id.btn_alreadyRead);
        btn_wantToRead = findViewById(R.id.btn_wantToRead);
        btn_currentlyReading = findViewById(R.id.btn_currentlyReading);
        btn_favourite = findViewById(R.id.btn_favourite);
        btn_about = findViewById(R.id.btn_about);
    }
}