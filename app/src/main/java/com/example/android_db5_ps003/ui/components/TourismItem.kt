package com.example.android_db5_ps003.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_db5_ps003.R
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage

@Composable
fun TourismItem(
    image: String,
    name: String,
    rating: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            AsyncImage(
                model = image,
                contentDescription = stringResource(R.string.tourism_image),
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = name,
            fontSize = 18.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Filled.Star,
                contentDescription = stringResource(R.string.rating_star),
                modifier = modifier.size(16.dp),
                tint = Color("#ffe234".toColorInt())
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = rating,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TourismItemPreview() {
    TourismItem(
        "https://lh3.googleusercontent.com/gps-cs-s/AB5caB-2OcRyNdGIFbr8gOz8iYVQ2tMaDugvbJDpY6E2yJd0BDJCxD9ZujArhTLafxT6f0skp-Nzlln1bhVNJpBrR8uMJQ0b_I-LUKugnFhgaodvGvwWzx1pPKV11sZKFzFzcSTtGX83=w408-h339-k-no",
        "Pantai Menganti Kebumen",
        "4.7"
    )
}

