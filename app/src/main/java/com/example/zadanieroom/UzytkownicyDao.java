package com.example.zadanieroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UzytkownicyDao {
    @Insert
    public void wstawDoBazy(Uzytkownik uzytkownik);
    @Delete
    public void usunZBazy(Uzytkownik uzytkownik);
    @Update
    public void aktualizujUzytkownikaWBazie(Uzytkownik uzytkownik);
    @Query("SELECT * FROM uzytkownicy")
    public List<Uzytkownik> zwrocWszystkichUzytkownikow();
}
