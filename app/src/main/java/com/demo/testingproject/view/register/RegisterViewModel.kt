package com.demo.testingproject.view.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.testingproject.base.BaseViewModel
import com.demo.testingproject.constant.TimeIntervals.QUARTER_SECOND_IN_MILLIS
import com.demo.testingproject.domain.model.general.ResultCall
import com.demo.testingproject.domain.model.request.RegisterRequest
import com.demo.testingproject.domain.usecase.RegisterUseCase
import com.demo.testingproject.enums.FieldType
import com.demo.testingproject.enums.FieldType.EMAIL
import com.demo.testingproject.enums.FieldType.FULL_NAME
import com.demo.testingproject.enums.FieldType.PASSWORD
import com.demo.testingproject.util.StateWrapper
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel() {

    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    sealed class Event {
        data class OnClickRegister(
            val email: String,
            val password: String,
            val name: String,
            val marketing: Boolean,
        ) : Event()
    }

    sealed class State {
        data class ShowLoading(val isLoading: Boolean) : State()
        data class ShowErrorField(val fieldType: FieldType) : State()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnClickRegister -> handleClickRegister(
                event.email,
                event.password,
                event.name,
                event.marketing
            )

            else -> {}
        }
    }

    private fun handleClickRegister(
        email: String,
        password: String,
        name: String,
        marketing: Boolean,
    ) = launch {

        if (!validateForm(email, password, name)) return@launch

        val request = RegisterRequest(
            email = email,
            password = password,
            name = name,
            marketingOptIn = marketing,
        )
        setState(State.ShowLoading(true))
        apiDelay(QUARTER_SECOND_IN_MILLIS)
        when (val result = registerUseCase.invoke(request)) {
            is ResultCall.Failed -> {
                Log.d("result", result.toString())
                setState(State.ShowLoading(false))
            }

            is ResultCall.Success -> {
                setState(State.ShowLoading(false))
            }
        }
    }

    private fun validateForm(
        email: String,
        password: String,
        name: String
    ): Boolean {

        var isValid = true

        if (email.isEmpty()) {
            setState(State.ShowErrorField(EMAIL))
            isValid = false
        }
        if (password.isEmpty()) {
            setState(State.ShowErrorField(PASSWORD))
            isValid = false
        }
        if (name.isEmpty()) {
            setState(State.ShowErrorField(FULL_NAME))
            isValid = false
        }

        return isValid
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }
}
