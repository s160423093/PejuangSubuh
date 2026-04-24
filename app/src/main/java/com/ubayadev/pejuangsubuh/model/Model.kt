package com.ubayadev.pejuangsubuh.model

data class Habit(
    var name:String?,
    var description: String?,
    var progress: Int = 0,
    var goal: Int?,
    var unit: String?,
    var icon: String
)