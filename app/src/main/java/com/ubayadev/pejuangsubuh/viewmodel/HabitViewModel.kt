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
import org.json.JSONObject

class HabitViewModel(application: Application): AndroidViewModel(application) {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val loadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val insertStatusLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        loadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())

        val url = "https://ubaya.cloud/hybrid/160423093/nmp/get_all_habits.php"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { //response ->
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if (obj.getString("status") == "SUCCESS") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Habit>>() {}.type
                    val result: List<Habit> = Gson().fromJson(data.toString(), sType)

                    habitsLD.value = ArrayList(result)
                    loadErrorLD.value = false
                    loadingLD.value = false
                }
            },
            { //error ->
                Log.d("apiresult", it.toString())
                loadErrorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun insert(habit: Habit){
        insertStatusLD.value = false
        loadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())

        val url = "https://ubaya.cloud/hybrid/160423093/nmp/insert_habit.php"
        val stringRequest = object : StringRequest(
            Method.POST,
            url,
            {
                Log.d("apiresult", it)
                insertStatusLD.value = true
                loadErrorLD.value = false
                loadingLD.value = false
            },
            {
                Log.d("apiresult", it.toString())
                insertStatusLD.value = false
                loadErrorLD.value = true
                loadingLD.value = false
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["name"] = habit.name.toString()
                params["description"] = habit.description.toString()
                params["goal"] = habit.goal.toString()
                params["unit"] = habit.unit.toString()
                params["icon"] = habit.icon.toString()
                params["progress"] = habit.progress.toString()
                return params
            }
        }
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}