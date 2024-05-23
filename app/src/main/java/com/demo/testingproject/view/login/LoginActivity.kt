package com.demo.testingproject.view.login

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.testingproject.R
import com.demo.testingproject.databinding.ActivityLoginBinding
import com.demo.testingproject.domain.model.general.FailedResult
import com.demo.testingproject.util.startActivity
import com.demo.testingproject.util.subscribeSingleState
import com.demo.testingproject.view.register.RegisterActivity
import com.demo.testingproject.view.register.RegisterViewModel
import com.demo.testingproject.widget.dialog.ErrorMessageDialog
import com.demo.testingproject.widget.loading.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingDialog = LoadingDialog(this)

        subscribeState()
        setupButton()
        setupTextView()
    }

    private fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when (it) {
                is LoginViewModel.State.ShowLoading -> showLoading(it.isLoading)
                is LoginViewModel.State.ShowFailed -> showFailed(it.failedResult)
                else -> {}
            }
        }
    }

    private fun setupButton() = with(binding) {
        btnLogin.setOnClickListener {
            if (validateForm())
                clickLogin()
        }
    }
    private fun setupTextView() = with(binding) {
        tvRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }

    private fun clickLogin() {
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            if (loadingDialog.isShowing) {
                loadingDialog.hide()
            }
            loadingDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            loadingDialog.setCancelable(false)
            loadingDialog.show()
        } else {
            loadingDialog.hide()
        }
    }

    private fun showFailed(failedResult: FailedResult) {
        val bottomSheetDialog = ErrorMessageDialog(
            this,
            title = failedResult.title,
            description = failedResult.description,
            onOkClickListener = {
                // Handle OK button click
            }
        )
        bottomSheetDialog.show()
    }
}
