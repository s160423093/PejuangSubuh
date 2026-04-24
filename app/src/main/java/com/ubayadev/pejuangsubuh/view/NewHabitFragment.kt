package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
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
        with(binding){
            btnCreate.setOnClickListener{ x ->
                val new = Habit(
                    name = txtName.text.toString(),
                    description = txtName.text.toString(),
                    goal = txtGoal.text.toString().toInt(),
                    unit = txtUnit.text.toString(),
                    icon = spinnerIcon.selectedItem.toString()
                )
                viewModel.insert(new)
                viewModel.insertStatusLD.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        val action = NewHabitFragmentDirections.actionNewHabitFragment()
                        x.findNavController().navigate(action)
                    }
                })
            }
        }
    }

    fun setupSpinner(){
        val items = arrayOf("Savings","Reading","Walking")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerIcon.adapter = adapter
    }
}