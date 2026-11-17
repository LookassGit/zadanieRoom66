package com.example.zadanieroom;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    UzytkownicyDataBase uzytkownicyDataBase;
    Button buttonDodaj;
    EditText editName, editSecondName, editEmail, editPhone;

    ArrayList<Uzytkownik> uzytkownicy;
    ArrayAdapter<Uzytkownik> arrayAdapter;
    ListView listaUzytkownikow;

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

        //uzytkownicyDataBase.zwrocWszystkichUzytkownikow().wstawDoBazy(new Uzytkownik("Kamil", "Mazurek", "kamilmaz@gmail.com", "32424324"));

        buttonDodaj = findViewById(R.id.buttonDodaj);
        editName = findViewById(R.id.editName);
        editSecondName = findViewById(R.id.editSecondName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);

        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uzytkownik uzytkownik = new Uzytkownik(
                        editName.getText().toString(),
                        editSecondName.getText().toString(),
                        editEmail.getText().toString(),
                        editPhone.getText().toString()
                );
                uzytkownicyDataBase.zwrocWszystkichUzytkownikow().wstawDoBazy(uzytkownik);

                uzytkownicy = new ArrayList<>(
                        uzytkownicyDataBase.zwrocWszystkichUzytkownikow().zwrocWszystkichUzytkownikow()
                );

                arrayAdapter = new ArrayAdapter<>(MainActivity.this, listaUzytkownikow);

                listaUzytkownikow.setAdapter(arrayAdapter);
            }
        });



    }
}