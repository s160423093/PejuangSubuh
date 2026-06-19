package com.ubayadev.pejuangsubuh.view

import android.view.View
import com.ubayadev.pejuangsubuh.model.Habit

interface HabitItemListener {
    fun onClick(view: View)
    fun onIncrease(habit: Habit)
    fun onDecrease(habit: Habit)
}