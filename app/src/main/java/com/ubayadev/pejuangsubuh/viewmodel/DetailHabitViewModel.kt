package com.ubayadev.pejuangsubuh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.model.HabitDatabase
import com.ubayadev.pejuangsubuh.utility.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailHabitViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    val habitLD = MutableLiveData<Habit>()
    val job = Job()
    //    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    fun fetch(id: Int) {
        launch{
            val db = buildDB(getApplication())
            habitLD.postValue(db.habitDao().selectHabit(id))
        }
    }

    fun addHabitObject(habit: Habit) {
        launch{
            buildDB(getApplication()).habitDao().insert(habit)
        }
    }

    fun addHabit(list: List<Habit>) {
        launch{
            val db = HabitDatabase.buildDatabase(getApplication())
            db.habitDao().insertAll(*list.toTypedArray())
        }
    }

    fun updateHabitObject(habit: Habit){
        launch{
            buildDB(getApplication()).habitDao().updateHabit(habit)
        }
    }

    fun updateHabit(id: Int, name: String, description: String){
        launch{
            val db = buildDB(getApplication())
            db.habitDao().updateHabit(id, name, description)
        }
    }
}