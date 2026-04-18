package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubayadev.pejuangsubuh.R
import com.ubayadev.pejuangsubuh.databinding.FragmentDashboardBinding
import com.ubayadev.pejuangsubuh.viewmodel.ListViewModel

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    lateinit var viewModel: ListViewModel
    val habitListAdapter = HabitListAdapter(arrayListOf())

    fun observeViewModel(){
        habitListAdapter.habitList
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }
}