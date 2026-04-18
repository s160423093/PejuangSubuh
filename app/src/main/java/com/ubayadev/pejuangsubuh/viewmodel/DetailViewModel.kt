package com.ubayadev.pejuangsubuh.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.model.User

class DetailViewModel: ViewModel() {
    val habitLD = MutableLiveData<Habit>()
    fun fetch(){
//        val user = User("")
//        val habit = Habit("")
//        habitLD.value = habit
    }
}

//Perkiraan ada dua ViewModeL untuk User dan Habit
//Kemungkinan dapat di InnerJoin