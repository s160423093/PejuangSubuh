package com.ubayadev.pejuangsubuh.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ubayadev.pejuangsubuh.R
import com.ubayadev.pejuangsubuh.databinding.HabitCardBinding
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.viewmodel.HabitViewModel

class HabitListAdapter(val habitList: ArrayList<Habit>, val viewModel: HabitViewModel): RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>() {
    class HabitViewHolder(var binding: HabitCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = HabitCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habitList[position]
        with(holder.binding){
            imgIcon.setImageResource(setIcon(habit.icon))
            txtName.text = habit.name
            txtDescription.text = habit.description
            txtStatus.text = setStatus(habit)
            txtGoalUnit.text = String.format("${habit.progress}/${habit.goal} ${habit.unit}")
            progressBar.max = habit.goal
            progressBar.setProgress(habit.progress, true)
            btnDecrease.setOnClickListener {
                if (habit.progress > 0) {
                    habit.progress -= 1
                    viewModel.update(habit)
                }
            }
            btnIncrease.setOnClickListener {
                if (habit.progress < habit.goal) {
                    habit.progress += 1
                    viewModel.update(habit)
                }
            }
        }
    }

    fun updateList(tempList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(tempList)
        notifyDataSetChanged()
    }

    fun setIcon(iconName: String?): Int {
        return when (iconName?.lowercase()) {
            "walking" -> R.drawable.ic_walking_foreground
            "savings" -> R.drawable.ic_savings_foreground
            "reading" -> R.drawable.ic_reading_foreground
            else -> R.drawable.ic_default_foreground
        }
    }

    fun setStatus(habit: Habit) = if (habit.progress >= habit.goal) { "Done" } else { "In Progress" }

    override fun getItemCount() = habitList.size
}