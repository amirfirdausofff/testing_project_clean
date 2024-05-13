package com.demo.testingproject.view.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.demo.testingproject.R
import com.demo.testingproject.databinding.ActivityLoginBinding
import com.demo.testingproject.databinding.ActivityMainBinding
import com.demo.testingproject.util.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButton()
    }

    private fun setupButton() = with(binding){
        btnLogin.setOnClickListener{
            if(validateForm())
                clickLogin()
        }
    }

    private fun clickLogin(){
        viewModel.onEvent(
            LoginViewModel.Event.OnClickLogin(
                binding.etUsername.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        )
    }

    private fun validateForm(): Boolean = with(binding) {
        var isValid = true

        if (etUsername.text.trim().isEmpty()) {
            etUsername.error = getString(R.string.la_err_username)
            isValid = false
        }
        if (etPassword.text.trim().isEmpty()) {
            etPassword.error = getString(R.string.la_err_password)
            isValid = false
        }
        return isValid
    }

}