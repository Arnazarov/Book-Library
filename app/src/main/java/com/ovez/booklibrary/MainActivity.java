package com.ovez.booklibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

        btn_alreadyRead.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
            startActivity(intent);
        });

        btn_about.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(getString(R.string.app_name));
            builder.setMessage("Developed by OA at oaCode.com\nCheck my website for more...");
            builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                    intent.putExtra("url", "https://github.com/Arnazarov");
                    startActivity(intent);
                }
            });

            builder.create().show();

        });

        Utils.getInstance();
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