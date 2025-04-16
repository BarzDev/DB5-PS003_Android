package com.example.android_db5_ps003.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun EmergencyLists(
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
            modifier = Modifier
                .padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmergencyListPreview() {
    Android_DB5PS003Theme {
        EmergencyLists(
            image = R.drawable.ic_catalogue,
            emergencyName = "Basarnas",
        )
    }
}