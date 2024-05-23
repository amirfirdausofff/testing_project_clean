package com.demo.testingproject.view.register

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.testingproject.R
import com.demo.testingproject.databinding.ActivityLoginBinding
import com.demo.testingproject.databinding.ActivityRegisterBinding
import com.demo.testingproject.domain.model.general.FailedResult
import com.demo.testingproject.enums.FieldType
import com.demo.testingproject.util.subscribeSingleState
import com.demo.testingproject.view.login.LoginViewModel
import com.demo.testingproject.widget.loading.LoadingDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.demo.testingproject.enums.FieldType.EMAIL
import com.demo.testingproject.enums.FieldType.FULL_NAME
import com.demo.testingproject.enums.FieldType.PASSWORD
import com.demo.testingproject.widget.dialog.ErrorMessageDialog

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    private lateinit var loadingDialog: LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingDialog = LoadingDialog(this)

        subscribeState()
        setupButton()
    }

    private fun subscribeState() {
        subscribeSingleState(viewModel.state) {
            when (it) {
                is RegisterViewModel.State.ShowLoading -> showLoading(it.isLoading)
                is RegisterViewModel.State.ShowErrorField -> showErrorField(it.fieldType)
                is RegisterViewModel.State.ShowFailed -> showFailed(it.failedResult)

                else -> {}
            }
        }
    }

    private fun setupButton() = with(binding){
        btnRegister.setOnClickListener{
            viewModel.onEvent(
                RegisterViewModel.Event.OnClickRegister(
                    etEmail.text.toString().trim(),
                    etPassword.text.toString().trim(),
                    etName.text.toString().trim(),
                    cbMarkerting.isChecked
                )
            )
        }
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

    private fun showErrorField(fieldType: FieldType) {
        when (fieldType) {
            EMAIL -> binding.etEmail.error =
                getString(R.string.this_field_is_required)

            PASSWORD -> binding.etPassword.error =
                getString(R.string.this_field_is_required)

            FULL_NAME -> binding.etName.error =
                getString(R.string.this_field_is_required)

            else -> Unit
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