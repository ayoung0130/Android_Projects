package com.example.calculator.ui.calculate

import androidx.lifecycle.ViewModel
import com.example.calculator.util.Operator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {
    private val _viewState = MutableStateFlow(CalculatorViewState())
    val viewState: StateFlow<CalculatorViewState> = _viewState

    fun operator(type: Operator) {
        val num1 = _viewState.value.inputNum1.toDoubleOrNull() ?: EMPTY_VALUE
        val num2 = _viewState.value.inputNum2.toDoubleOrNull() ?: EMPTY_VALUE
        val calculationResult = Operator.calculate(type, num1, num2)

        _viewState.value = _viewState.value.copy(result = calculationResult.toString())
    }

    fun onInputNum1Change(newValue: String) {
        _viewState.value = _viewState.value.copy(inputNum1 = newValue)
    }

    fun onInputNum2Change(newValue: String) {
        _viewState.value = _viewState.value.copy(inputNum2 = newValue)
    }

    companion object {
        private const val EMPTY_VALUE = 0.0
    }
}