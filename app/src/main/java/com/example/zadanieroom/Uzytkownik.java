package com.example.zadanieroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "uzytkownicy")

public class Uzytkownik {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private String telefon;

}
