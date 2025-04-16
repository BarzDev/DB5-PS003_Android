package com.example.android_db5_ps003.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun NewsListItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    headlineText: String,
    shortDesc: String,
    date: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(height = 120.dp, width = 100.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(
                text = headlineText,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = shortDesc,
                fontSize = 11.sp,
                fontWeight = FontWeight.Light,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify
            )
            Text(
                text = date,
                fontSize = 11.sp,
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsListItemPreview() {
    Android_DB5PS003Theme {
        NewsListItem(
            imageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgK8X-TDptGdkQHjhh_EX6-mOfThQbLytuxWL97i2m6FjhFQUECCcN1JVukCK1hkvGkK9wl7R0WjClefYSavc93tmUsGT2KVri1gqy0paLE7PECqCOOs_8GU2bxBdzicBL9wCaxInafYv9X7N5RKXx1lFjgsvYbfXT6eGStCXABcTS2PcsyZpcRnAhluG9U/s4000/20250307_090515.jpg",
            headlineText = "Universitas Putra Bangsa Kembangkan Teknologi Smart Environmental Control (SEC) untuk Budidaya Melon",
            shortDesc = "Program Studi Agribisnis dan Program Studi Ilmu Komputer, Fakultas Sains dan Teknologi, Universitas Putra Bangsa berhasil mengembangkan teknologi terapan berbasis Internet of Things (IoT) bernama Smart Environmental Control (SEC). Teknologi ini diterapkan dalam budidaya tanaman melon di rumah kaca dan berfokus pada pengaturan lingkungan tumbuh secara otomatis guna mencapai kondisi optimal bagi tanaman",
            date = "26 Maret 2025"
        )
    }
}