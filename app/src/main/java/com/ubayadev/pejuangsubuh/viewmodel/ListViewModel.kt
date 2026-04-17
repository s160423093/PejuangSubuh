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

class ListViewModel(application: Application): AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volley_Tag"

    var queue : RequestQueue? = null

    fun refresh(){
        loadingLD.value = true
        loadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())

        val url = ""
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                val sType = object: TypeToken<List<Habit>>(){}.type
                val result: List<Habit> = Gson().fromJson(it, sType)
                habitsLD.value = result as ArrayList<Habit>?

                loadingLD.value = false
                Log.d("show_volley", it)
            },
            {
                Log.d("show_volley", it.toString())
                loadErrorLD. value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
        loadErrorLD.value = false
        loadingLD.value = false
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}