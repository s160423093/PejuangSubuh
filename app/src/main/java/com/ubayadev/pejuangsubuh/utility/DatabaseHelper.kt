package com.ubayadev.pejuangsubuh.utility

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ubayadev.pejuangsubuh.model.HabitDatabase

val  DB_NAME = "pejuang_subuh"

fun buildDB(context: Context): HabitDatabase{
    val db = HabitDatabase.buildDatabase(context)
    return db
}

//val MIGRATION_1_2 = object : Migration(1,2){
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL()
//    }
//}