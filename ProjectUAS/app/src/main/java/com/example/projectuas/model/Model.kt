package com.example.projectuas.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @ColumnInfo("image")
    var image:String,
    @ColumnInfo("title")
    var title:String,
    @ColumnInfo("author")
    var author:String,
    @ColumnInfo("desc")
    var desc:String,
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}

@Entity
data class Users(
    @ColumnInfo("first_name")
    var first_name:String,
    @ColumnInfo("last_name")
    var last_name:String,
    @ColumnInfo("email")
    var email:String,
    @ColumnInfo("username")
    var username:String,
    @ColumnInfo("password")
    var password:String,
    @ColumnInfo("photo")
    var photo:String
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}