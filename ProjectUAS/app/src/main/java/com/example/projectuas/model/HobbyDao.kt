package com.example.projectuas.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
@Dao
interface HobbyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(vararg news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllUser(vararg users: Users)

    @Query("SELECT * FROM news")
    fun selectAllNews():List<News>

    @Query("SELECT * FROM news WHERE uuid=:id")
    fun selectNews(id:Int):News

    @Update
    fun updateNews(news: News)

    @Delete
    fun deleteNews(news:News)

    @Query("SELECT * FROM users")
    fun selectAllUser():List<Users>

    @Query("SELECT * FROM users WHERE uuid=:id")
    fun selectUser(id:Int):Users

    @Query("SELECT * FROM users WHERE username=:username AND password=:password")
    fun loginUser(username:String, password:String):Users

    @Update
    fun updateUser(users: Users)

    @Query("UPDATE users SET first_name=:firstName, last_name=:lastName, password=:password WHERE uuid=:id")
    fun updateUser(firstName:String, lastName:String, password: String, id: Int)

    @Delete
    fun deleteUser(users: Users)
}