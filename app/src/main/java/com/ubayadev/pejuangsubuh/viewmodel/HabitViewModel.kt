package com.ubayadev.pejuangsubuh.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.utility.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class HabitViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val habitsLD = MutableLiveData<List<Habit>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val insertStatusLD = MutableLiveData<Boolean>()
    val updateStatusLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        loadErrorLD.value = false
        launch {
            try {
                val db = buildDB(getApplication())
                habitsLD.postValue(db.habitDao().selectAllHabit())
                loadingLD.postValue(false)
            } catch (e: Exception) {
                loadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    fun insert(habit: Habit) {
        loadingLD.value = true
        loadErrorLD.value = false
        launch {
            try {
                val db = buildDB(getApplication())
                db.habitDao().insert(habit)
                insertStatusLD.postValue(true)
                loadingLD.postValue(false)
            } catch (e: Exception) {
                insertStatusLD.postValue(false)
                loadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    fun update(habit: Habit) {
        loadingLD.value = true
        loadErrorLD.value = false
        launch {
            try {
                val db = buildDB(getApplication())
                db.habitDao().updateHabit(habit)
                updateStatusLD.postValue(true)
                habitsLD.postValue(db.habitDao().selectAllHabit())
                loadingLD.postValue(false)
            } catch (e: Exception) {
                updateStatusLD.postValue(false)
                loadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}