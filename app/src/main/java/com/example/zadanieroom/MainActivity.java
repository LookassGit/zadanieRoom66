package com.example.zadanieroom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    Button buttonDodaj, buttonEdytuj;
    EditText editName, editSecondName, editEmail, editPhone;

    List<Uzytkownik> uzytkownicy = new ArrayList<>();
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
        listaUzytkownikow = findViewById(R.id.listView);
        buttonEdytuj = findViewById(R.id.buttonEdytuj);

        uzytkownicy = uzytkownicyDataBase.zwrocWszystkichUzytkownikow().zwrocWszystkichUzytkownikow();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, uzytkownicy);

        listaUzytkownikow.setAdapter(arrayAdapter);

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
                uzytkownicy.add(uzytkownik);
                arrayAdapter.notifyDataSetChanged();

            }
        });
        listaUzytkownikow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                buttonEdytuj.setVisibility(View.VISIBLE);
                Uzytkownik wybranyUzytkownik = uzytkownicy.get(i);
                editName.setText(wybranyUzytkownik.getImie());
                editSecondName.setText(wybranyUzytkownik.getNazwisko());
                editEmail.setText(wybranyUzytkownik.getEmail());
                editPhone.setText(wybranyUzytkownik.getTelefon());

                buttonEdytuj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        wybranyUzytkownik.setImie(editName.getText().toString());
                        wybranyUzytkownik.setNazwisko(editSecondName.getText().toString());
                        wybranyUzytkownik.setEmail(editEmail.getText().toString());
                        wybranyUzytkownik.setTelefon(editPhone.getText().toString());

                        editName.setText("");
                        editSecondName.setText("");
                        editEmail.setText("");
                        editPhone.setText("");

                        uzytkownicyDataBase.zwrocWszystkichUzytkownikow().aktualizujUzytkownikaWBazie(wybranyUzytkownik);
                        arrayAdapter.notifyDataSetChanged();

                        buttonEdytuj.setVisibility(View.INVISIBLE);
                    }
                });
                return true;
            }
        });

       listaUzytkownikow.setOnItemClickListener(
               new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       Uzytkownik uzytkownikDoUsuniecia = uzytkownicy.get(i);

                       uzytkownicy.remove(uzytkownikDoUsuniecia);
                       uzytkownicyDataBase.zwrocWszystkichUzytkownikow().usunZBazy(uzytkownikDoUsuniecia);
                       arrayAdapter.notifyDataSetChanged();
                   }
               }
       );
    }
}