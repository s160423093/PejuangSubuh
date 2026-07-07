package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.ubayadev.pejuangsubuh.databinding.FragmentLoginBinding
import com.ubayadev.pejuangsubuh.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.seedUser()

        observeViewModel()

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            viewModel.login(username, password)
        }
    }

    fun observeViewModel() {
        viewModel.loginResult.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val action = LoginFragmentDirections.actionLoginFragment()
                Navigation.findNavController(binding.root).navigate(action)
            } else {
                Toast.makeText(view?.context, "Username/Password Salah!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}