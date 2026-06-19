package com.ubayadev.pejuangsubuh.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habit: Habit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(habit: Habit)

    @Query("SELECT * FROM habits ORDER BY progress DESC")
    fun selectAllHabit(): List<Habit>

    @Query("SELECT * FROM habits WHERE id= :id")
    fun selectHabit(id:Int): Habit

    @Query("UPDATE habits SET name= :name, description= :description WHERE id= :id")
    fun updateHabit(id: Int, name: String, description: String)

    @Update
    fun updateHabit(habit: Habit)

    @Delete
    fun deleteHabit(habit: Habit)
}