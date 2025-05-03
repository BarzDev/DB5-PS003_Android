package com.example.android_db5_ps003.ui.components.emergency_call

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun Emergency_Public_ItemLayout(
    modifier: Modifier = Modifier,
    image: Int,
    emergencyName: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
        )
        Text(
            text = emergencyName,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Emergency_Public_ItemLayoutPreview() {
    Android_DB5PS003Theme {
        Emergency_Public_ItemLayout(
            image = R.drawable.ic_catalogue,
            emergencyName = "Basarnas",
        )
    }
}
