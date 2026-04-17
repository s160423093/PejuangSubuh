package com.ubayadev.pejuangsubuh.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.pejuangsubuh.databinding.FragmentDashboardBinding
import com.ubayadev.pejuangsubuh.databinding.HabitCardBinding
import com.ubayadev.pejuangsubuh.model.Habit

class HabitListAdapter(val habitList: ArrayList<Habit>): RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class HabitViewHolder(var binding: HabitCardBinding): RecyclerView.ViewHolder(binding.root)
}