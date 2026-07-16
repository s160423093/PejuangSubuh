package com.ubayadev.pejuangsubuh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.pejuangsubuh.model.User
import com.ubayadev.pejuangsubuh.utility.FileHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import com.ubayadev.pejuangsubuh.utility.buildDB

class LoginViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val loginResult = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun login(username: String, password: String) {
        launch {
            val db = buildDB(getApplication())
            val user = db.userDao().checkLogin(username, password)

            if (user != null) {
                loginResult.postValue(true)
            } else {
                loginResult.postValue(false)
            }
        }
    }

    fun seedUser() {
        launch {
            val db = buildDB(getApplication())

            if (db.userDao().countUser() == 0) {
                db.userDao().insertUser(
                    User(
                        username = "student",
                        password = "123"
                    )
                )
            }
        }
    }
    fun saveSession(username: String) {
        val sessionHelper = FileHelper(getApplication(), "session", "session.txt")
        sessionHelper.writeToFile(username)
    }

    fun checkSession(): String {
        val sessionHelper = FileHelper(getApplication(), "session", "session.txt")
        return if (sessionHelper.isSessionExist()) sessionHelper.readFromFile() else ""
    }

    fun clearSession() {
        val sessionHelper = FileHelper(getApplication(), "session", "session.txt")
        sessionHelper.deleteFile()
    }
}