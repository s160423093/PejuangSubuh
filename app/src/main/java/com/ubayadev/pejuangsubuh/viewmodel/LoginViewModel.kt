package com.ubayadev.pejuangsubuh.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    val loginResult = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        if (username == "student" && password == "123") {
            loginResult.value = true
        } else {
            loginResult.value = false
        }
    }
}