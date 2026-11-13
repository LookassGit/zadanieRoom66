package com.example.zadanieroom;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    UzytkownicyDataBase uzytkownicyDataBase;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        uzytkownicyDataBase = Room.databaseBuilder(
                        getApplicationContext(),
                        UzytkownicyDataBase.class,
                        "baza_uzytkownikow")
                .allowMainThreadQueries()
                .build();

        // uzytkownicyDataBase.zwrocWszystkichUzytkownikow().wstawDoBazy(new Uzytkownik("Kamil", "Mazurek", "kamilmaz@gmail.com", "32424324"));
    }
}