package com.demo.testingproject.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.testingproject.base.BaseViewModel
import com.demo.testingproject.constant.TimeIntervals.QUARTER_SECOND_IN_MILLIS
import com.demo.testingproject.domain.model.general.FailedResult
import com.demo.testingproject.domain.model.general.ResultCall
import com.demo.testingproject.domain.model.request.LoginRequest
import com.demo.testingproject.domain.usecase.LoginUseCase
import com.demo.testingproject.util.StateWrapper
import com.demo.testingproject.view.register.RegisterViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel() {


    private val _state = MutableLiveData<StateWrapper<State>>()
    val state: LiveData<StateWrapper<State>> = _state

    sealed class Event {
        data class OnClickLogin(val email: String, val password: String) : Event()
    }

    sealed class State {
        data class ShowLoading(val isLoading: Boolean) : State()
        data class ShowFailed(val failedResult: FailedResult) : State()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnClickLogin -> handleOnClickLogin(event.email, event.password)
            else -> {}
        }
    }

    private fun handleOnClickLogin(email: String, password: String) = launch {
        val request = LoginRequest(
            email = email,
            password = password
        )
        setState(State.ShowLoading(true))
        apiDelay(QUARTER_SECOND_IN_MILLIS)
        when (val result = loginUseCase.invoke(request)) {
            is ResultCall.Failed -> {
                setState(State.ShowLoading(false))
                setState(State.ShowFailed(result.failedResult))
            }
            is ResultCall.Success -> {
                setState(State.ShowLoading(false))
            }
        }
    }

    private fun setState(state: State) {
        _state.value = StateWrapper(state)
    }
}
