package com.example.calculator.ui.calculate.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.calculator.util.Operator


@Composable
fun Operator(
    modifier: Modifier = Modifier,
    onClick : (Operator) -> Unit
) {
    val operateList = OperatorButtonType.entries

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth().then(modifier)
    ) {
        operateList.forEach {
            OperatorButton(type = it, onClick = {
                onClick(it)
                keyboardController?.hide()
            })
        }
    }
}