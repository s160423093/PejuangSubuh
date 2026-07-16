package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ubayadev.pejuangsubuh.R
import com.ubayadev.pejuangsubuh.databinding.FragmentDashboardBinding
import com.ubayadev.pejuangsubuh.databinding.FragmentEditHabitBinding
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.viewmodel.DetailHabitViewModel


class EditHabitFragment : Fragment(), HabitItemListener {
    lateinit var binding: FragmentEditHabitBinding
    lateinit var viewModel: DetailHabitViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailHabitViewModel::class.java]

        binding.viewModel = viewModel
        binding.listener = this
        binding.lifecycleOwner = viewLifecycleOwner

        setupSpinner()

        val habitId = EditHabitFragmentArgs.fromBundle(requireArguments()).habitId
        viewModel.fetch(habitId)

        observeViewModel()
    }

    fun setupSpinner(){
        val items = arrayOf("Savings", "Reading", "Walking")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter
    }

    fun observeViewModel(){
        viewModel.habitLD.observe(viewLifecycleOwner, Observer { habitData ->
            binding.habit = habitData

            if (habitData != null) {
                val adapter = binding.spinnerIcon.adapter as ArrayAdapter<String>
                val position = adapter.getPosition(habitData.icon)
                if (position >= 0) {
                    binding.spinnerIcon.setSelection(position)
                }
            }
        })
    }

    override fun onClick(view: View) {
        val currentHabit = binding.habit
        if (currentHabit != null) {
            currentHabit.icon = binding.spinnerIcon.selectedItem.toString()
            viewModel.saveHabitChanges(currentHabit)
            view.findNavController().popBackStack()
        }
    }

    override fun onIncrease(habit: Habit) {
        //
    }

    override fun onDecrease(habit: Habit) {
        //
    }
}