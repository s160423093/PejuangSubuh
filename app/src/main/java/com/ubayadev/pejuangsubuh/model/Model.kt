package com.ubayadev.pejuangsubuh.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "progress")
    var progress: Int = 0,

    @ColumnInfo(name = "goal")
    var goal: Int,

    @ColumnInfo(name = "unit")
    var unit: String,

    @ColumnInfo(name = "icon")
    var icon: String,

    @ColumnInfo(name = "created_at")
    var createdAt: String? = null
)

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "password")
    var password: String
)