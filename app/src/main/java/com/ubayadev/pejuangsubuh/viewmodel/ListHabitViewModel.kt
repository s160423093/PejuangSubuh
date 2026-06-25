package com.ubayadev.pejuangsubuh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.model.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListHabitViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val habitLD = MutableLiveData<List<Habit>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    var job = Job()
//    override val coroutineContext: CoroutineContext = job + Dispatchers.IO
    override val coroutineContext: CoroutineContext get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        loadErrorLD.value = false
        launch {
            val db = AppDatabase.buildDatabase(getApplication())
            habitLD.postValue(db.habitDao().selectAllHabit())
            loadingLD.postValue(false)
        }
    }

    fun clearTask(habit: Habit) {
        launch {
            val db = AppDatabase.buildDatabase(getApplication())
            db.habitDao().deleteHabit(habit)
            habitLD.postValue(db.habitDao().selectAllHabit())
        }
    }
}