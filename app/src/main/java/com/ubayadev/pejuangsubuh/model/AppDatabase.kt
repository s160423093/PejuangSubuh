package com.ubayadev.pejuangsubuh.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubayadev.pejuangsubuh.utility.DB_NAME

@Database(entities = [Habit::class, User::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao
    companion object {
        @Volatile private var instance: AppDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
            AppDatabase::class.java, DB_NAME)
            .build()

        operator fun invoke(context:Context) {
            if(instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}