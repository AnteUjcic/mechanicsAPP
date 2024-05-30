package com.example.mechanicsapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import androidx.room.OnConflictStrategy;


@Dao
public interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(LoginInfo loginInfo);

    @Query("SELECT * FROM login_info")
    List<LoginInfo> getAllLogins();

    @Query("SELECT * FROM login_info WHERE username = :username AND password = :password LIMIT 1")
    LoginInfo login(String username, String password);

    @Query("SELECT * FROM login_info WHERE username = :username LIMIT 1")
    LoginInfo getUserByUsername(String username);

    @Query("SELECT * FROM login_info WHERE oib = :oib LIMIT 1")
    LoginInfo getLoginInfoByOib(long oib);
    @Query("DELETE FROM login_info WHERE oib = :oib")
    void deleteByOib(long oib);
}
