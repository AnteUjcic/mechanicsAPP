package com.example.mechanicsapp;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoginDao {
    @Insert
    void insert(LoginInfo login);

    @Query("SELECT * FROM login_info")
    List<LoginInfo> getAllLogins();

    @Query("SELECT * FROM login_info WHERE username = :username AND password = :password LIMIT 1")
    LoginInfo login(String username, String password);

    @Query("SELECT * FROM login_info WHERE username = :username LIMIT 1")
    LoginInfo getUserByUsername(String username);

}
