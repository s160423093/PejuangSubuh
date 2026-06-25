package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubayadev.pejuangsubuh.R
import com.ubayadev.pejuangsubuh.databinding.FragmentDashboardBinding
import com.ubayadev.pejuangsubuh.databinding.FragmentEditHabitBinding


class EditHabitFragment : Fragment() {
    lateinit var binding: FragmentEditHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditHabitBinding.inflate(inflater, container, false)
        return binding.root
    }


}