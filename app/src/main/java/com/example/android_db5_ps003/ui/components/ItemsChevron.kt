package com.example.android_db5_ps003.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun ItemsChevron(
    modifier: Modifier = Modifier,
    fieldName : String
    ) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = fieldName,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
        Icon(
            painter = painterResource(R.drawable.chevron),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(8.dp)
                .clickable {  }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemsChevronPreview() {
    Android_DB5PS003Theme {
        ItemsChevron(
            fieldName = "Pelayanan Publik"
        )
    }
}