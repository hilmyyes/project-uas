package com.example.projectuas.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projectuas.model.NewsDatabase

val DB_NAME = "newsdb"

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE Users (
                uuid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                first_name TEXT NOT NULL, 
                last_name TEXT NOT NULL, 
                email TEXT NOT NULL, 
                username TEXT NOT NULL, 
                password TEXT NOT NULL, 
                photo TEXT NOT NULL
            )
            """)

        //delete news column from News table
        db.execSQL(
            """
            CREATE TABLE news_new (
                uuid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                image TEXT NOT NULL, 
                title TEXT NOT NULL, 
                author TEXT NOT NULL, 
                desc TEXT NOT NULL
            )
            """
        )

        // Copy the data from the old News table to the new table
        db.execSQL(
            """
            INSERT INTO news_new (uuid, image, title, author, desc) 
            SELECT uuid, image, title, author, desc 
            FROM News
            """
        )

        // Drop the old News table
        db.execSQL("DROP TABLE News")

        // Rename the new table to the old table's name
        db.execSQL("ALTER TABLE news_new RENAME TO News")
    }
}

fun buildDb(context:Context):NewsDatabase {
    val db = NewsDatabase.buildDatabase(context)
    return db
}