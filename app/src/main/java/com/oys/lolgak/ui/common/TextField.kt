package com.oys.lolgak.ui.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.oys.lolgak.ui.theme.Gray300
import com.oys.lolgak.ui.theme.body2

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hintText: String = "",
    maxLength: Int = Int.MAX_VALUE,
) {
    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength) {
                onValueChange.invoke(it)
            }
        },
        textStyle = body2,
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        style = body2, color = Gray300,
                    )
                }
                innerTextField()
            }
        }
    )
}