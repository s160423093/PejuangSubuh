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
import com.ubayadev.pejuangsubuh.viewmodel.HabitViewModel

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    lateinit var viewModel: HabitViewModel
    val habitListAdapter = HabitListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HabitViewModel::class.java]
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
            habitListAdapter.updateList(it)
        })

        viewModel.loadErrorLD.observe(viewLifecycleOwner, Observer {
            with(binding) {
                if(it == true) { txtError.visibility = View.VISIBLE } else { txtError.visibility = View.GONE }
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
}