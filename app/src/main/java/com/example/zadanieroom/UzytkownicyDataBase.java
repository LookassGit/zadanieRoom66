package com.example.zadanieroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Uzytkownik.class}, version = 1)
public abstract class UzytkownicyDataBase extends RoomDatabase {
    public abstract UzytkownicyDao zwrocWszystkichUzytkownikow();
}
