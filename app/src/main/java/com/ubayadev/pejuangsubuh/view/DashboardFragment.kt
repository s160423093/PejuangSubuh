package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubayadev.pejuangsubuh.databinding.FragmentDashboardBinding
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.viewmodel.HabitViewModel
import kotlin.toString

class DashboardFragment : Fragment(), HabitItemListener {
    lateinit var binding: FragmentDashboardBinding
    lateinit var viewModel: HabitViewModel
    lateinit var habitListAdapter: HabitListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]

        habitListAdapter = HabitListAdapter(arrayListOf(), this)

        viewModel.refresh()

        with(binding){
            recViewHabits.layoutManager = LinearLayoutManager(context)
            recViewHabits.adapter = habitListAdapter

            observeViewModel()

            refreshLayout.setOnRefreshListener {
                binding.recViewHabits.visibility = View.GONE
                binding.txtError.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE

                viewModel.refresh()
                binding.refreshLayout.isRefreshing = false
            }

            btnNewHabit.setOnClickListener {
                val action = DashboardFragmentDirections.actionDashboardFragment()
                it.findNavController().navigate(action)
            }
        }
    }

    fun observeViewModel(){
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            habitListAdapter.updateList(ArrayList(it))
        })

        viewModel.updateStatusLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
//                viewModel.refresh()
                viewModel.updateStatusLD.value = false
            }
        })

        viewModel.loadErrorLD.observe(viewLifecycleOwner, Observer {
            with(binding) {
                if(it == true) {
                    txtError.visibility = View.VISIBLE
                } else {
                    txtError.visibility = View.GONE
                }
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            with(binding) {
                if(it == true) {
                    recViewHabits.visibility = View.GONE
                    progressLoad.visibility = View.VISIBLE
                } else {
                    recViewHabits.visibility = View.VISIBLE
                    progressLoad.visibility = View.GONE
                }
            }
        })
    }

    override fun onClick(view: View) {
        val habitId = view.tag.toString().toInt()
        val action = DashboardFragmentDirections.actionDashboardEditFragment(habitId)
        view.findNavController().navigate(action)
    }

    override fun onIncrease(habit: Habit) {
        if (habit.progress < habit.goal) {
            habit.progress++
            viewModel.update(habit)
        }
    }

    override fun onDecrease(habit: Habit) {
        if (habit.progress > 0) {
            habit.progress--
            viewModel.update(habit)
        }
    }
}