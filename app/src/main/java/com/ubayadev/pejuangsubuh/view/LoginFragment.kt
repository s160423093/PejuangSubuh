package com.ubayadev.pejuangsubuh.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.ubayadev.pejuangsubuh.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            btnLogin.setOnClickListener {
                val username = txtUsername.text.toString()
                val password = txtPassword.text.toString()
                if(username != "student" || password != "123"){
                    Toast.makeText(requireContext(),"Username/Password Salah!", Toast.LENGTH_SHORT).show()
                } else {
                    val action = LoginFragmentDirections.actionLoginFragment()
                    it.findNavController().navigate(action)
                }
            }
        }
    }
}