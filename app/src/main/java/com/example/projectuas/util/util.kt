package com.example.projectuas.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projectuas.model.NewsDatabase

val DB_NAME = "newsdb"

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {

        // Drop the old News table
        db.execSQL("DROP DATABASE hobbydb")

        // check databases column users
        db.execSQL( "CREATE TABLE IF NOT EXISTS `Users` (" +
                "`uuid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "`firstName` TEXT, " +
                "`lastName` TEXT, " +
                "`email` TEXT, " +
                "`password` TEXT, " +
                "`photo` TEXT, " +
                "`username` TEXT)")

        // check databases column news
        db.execSQL( "CREATE TABLE IF NOT EXISTS `News` (" +
                "`uuid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "`image` TEXT, " +
                "`title` TEXT, " +
                "`author` TEXT, " +
                "`desc` TEXT, ")
    }

}


fun buildDb(context:Context):NewsDatabase {
    val db = NewsDatabase.buildDatabase(context)
    return db
}