package com.example.android_db5_ps003.ui.components.kuliner

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.remote.response.DataItem
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Item_row(
    kuliner: DataItem,
    modifier: Modifier = Modifier,
    onItemClick: (Int?) -> Unit
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium,
                spotColor = Color.Black.copy(alpha = 0.3f)
            )
            .clickable { onItemClick(kuliner.id) },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(
                    model = kuliner.img,
                    placeholder = painterResource(R.drawable.ic_refresh_black)
                ),
                contentDescription = kuliner.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = kuliner.name ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )

                if (!kuliner.category.isNullOrEmpty()) {
                    Text(
                        text = kuliner.category,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Text(
                    text = "Rp. ${
                        kuliner.price?.let {
                            NumberFormat.getNumberInstance(Locale.US).format(it)
                        }
                    }",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val ratingValue = when (kuliner.ratings) {
                        is Double -> kuliner.ratings
                        is Int -> kuliner.ratings.toDouble()
                        is String -> kuliner.ratings.toDoubleOrNull() ?: 0.0
                        else -> 0.0
                    }

                    StarRating(
                        rating = ratingValue,
                        maxStars = 5,
                        starSize = 16.dp
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "%.1f".format(ratingValue),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun StarRating(
    rating: Double,
    maxStars: Int = 5,
    starSize: androidx.compose.ui.unit.Dp = 24.dp
) {
    val filledStars = rating.toInt()
    val halfStar = rating - filledStars >= 0.5

    Row {
        repeat(filledStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "Filled star",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(starSize)
            )
        }

        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_half),
                contentDescription = "Half star",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(starSize)
            )
        }

        repeat(maxStars - filledStars - if (halfStar) 1 else 0) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_outline),
                contentDescription = "Outline star",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(starSize)
            )
        }
    }
}