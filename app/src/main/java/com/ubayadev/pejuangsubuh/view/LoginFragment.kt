package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ubayadev.pejuangsubuh.databinding.FragmentLoginBinding
import com.ubayadev.pejuangsubuh.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel.loginResult.observe(viewLifecycleOwner) {
            if (it == true) {
                val action = LoginFragmentDirections.actionLoginFragment()
                view.findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Username/Password Salah!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.login(username, password)
        }
    }

}