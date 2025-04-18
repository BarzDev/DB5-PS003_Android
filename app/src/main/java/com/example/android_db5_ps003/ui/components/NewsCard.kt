package com.example.android_db5_ps003.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun NewsCardItem(
    modifier: Modifier = Modifier,
    id: Long,
    headlineText: String,
    urlImg: String,
    date: String,
    navigateToNewsDetail: (Long) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Gray
        ),
        modifier = modifier
            .size(height = Dp.Unspecified, width = 200.dp)
            .padding(horizontal = 4.dp)
            .clickable {
                navigateToNewsDetail(id)
            }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(urlImg)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
            )
            Text(
                text = date,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = headlineText,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun NewsCardItemPreview() {
    Android_DB5PS003Theme {
        NewsCardItem(
            headlineText = "Kondisi Politik di Pulaunya, walaupun aslinya milik Yaman tapi dilirik UEA",
            urlImg = "https://www.edupac-id.com/wp-content/uploads/2023/05/kuliah-di-dubai-scaled.jpg",
            date = "26 Maret 2025",
            navigateToNewsDetail = {}
        )
    }
}*/
