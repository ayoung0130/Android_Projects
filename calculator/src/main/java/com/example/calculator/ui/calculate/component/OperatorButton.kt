package com.example.calculator.ui.calculate.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calculator.util.Operator






enum class OperatorButtonType(val title: String, val operator: Operator) {
    Plus("PLUS", Operator.PLUS),
    MIN("MIN", Operator.MIN),
    MUL("MUL", Operator.MUL),
    DIV("DIV", Operator.DIV)
}

@Composable
fun OperatorButton(
    modifier: Modifier = Modifier,
    type: OperatorButtonType,
    onClick: (Operator) -> Unit
) {
    Button(
        modifier = Modifier.then(modifier),
        onClick = { onClick(type.operator) }
    ) {
        Text(text = type.title)
    }
}