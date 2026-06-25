package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ubayadev.pejuangsubuh.databinding.FragmentNewHabitBinding
import com.ubayadev.pejuangsubuh.model.Habit
import com.ubayadev.pejuangsubuh.viewmodel.HabitViewModel

class NewHabitFragment : Fragment() {
    lateinit var binding: FragmentNewHabitBinding
    lateinit var viewModel: HabitViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]
        setupSpinner()
        observeViewModel()

        binding.btnCreate.setOnClickListener {
            val newHabit = Habit(
                name = binding.txtName.text.toString(),
                description = binding.txtDescription.text.toString(),
                goal = binding.txtGoal.text.toString().toIntOrNull() ?: 0,
                unit = binding.txtUnit.text.toString(),
                icon = binding.spinnerIcon.selectedItem.toString()
            )
            viewModel.insert(newHabit)
        }
    }

    fun setupSpinner(){
        val items = arrayOf("Savings","Reading","Walking")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter
    }

    fun observeViewModel(){
        viewModel.insertStatusLD.observe(viewLifecycleOwner) {
            if (it) {
                view?.findNavController()?.popBackStack()
                viewModel.insertStatusLD.value = false
            }
        }
    }
}