package com.ubayadev.pejuangsubuh.utility

import android.content.Context
import com.ubayadev.pejuangsubuh.model.AppDatabase

val DB_NAME = "pejuang_subuh"

fun buildDB(context: Context): AppDatabase{
    val db = AppDatabase.buildDatabase(context)
    return db
}

//val MIGRATION_1_2 = object : Migration(1,2){
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL()
//    }
//}