package com.ubayadev.pejuangsubuh.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Habit::class, User::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao
    companion object {
        const val DB_NAME = "pejuang_subuh"
        @Volatile private var instance: AppDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, DB_NAME)
            .build()

        operator fun invoke(context:Context): AppDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
    }
}