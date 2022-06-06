package com.example.uberchicks.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [CartDatabaseModel::class], version = 1, exportSchema = false)
abstract class UberChicksDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao

    class Callback @Inject constructor(
        private val database: Provider<UberChicksDatabase>
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val dao =   database.get().cartDao()
        }
    }

}