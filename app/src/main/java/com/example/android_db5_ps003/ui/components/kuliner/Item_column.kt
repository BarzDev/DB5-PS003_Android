package com.example.android_db5_ps003.ui.components.kuliner

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.remote.response.DataItem
import java.text.NumberFormat
import java.util.Locale

@Composable
fun Item_column(
    kuliner: DataItem,
    modifier: Modifier = Modifier,
    onItemClick: (Int?) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(kuliner.id) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .shadow(
                elevation = 8.dp,
                shape = MaterialTheme.shapes.medium,
                clip = true
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = kuliner.img),
                contentDescription = kuliner.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = kuliner.name ?: "",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                if (!kuliner.category.isNullOrEmpty()) {
                    Text(
                        text = kuliner.category ?: "",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Rp. ${kuliner.price?.let {
                        NumberFormat.getNumberInstance(Locale.US).format(it)
                    }}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val rating = kuliner.ratings?.toString()?.toDoubleOrNull() ?: 0.0
                    StarRating(
                        rating = rating,
                        maxStars = 5,
                        starSize = 16.dp,
                        activeColor = MaterialTheme.colorScheme.primary,
                        inactiveColor = Color.LightGray
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "%.1f".format(rating),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
    starSize: Dp = 24.dp,
    activeColor: Color = Color(0xFFFFC107),
    inactiveColor: Color = Color.LightGray
) {
    val filledStars = rating.toInt()
    val halfStar = rating - filledStars >= 0.5

    Row {
        repeat(filledStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "Filled star",
                tint = activeColor,
                modifier = Modifier.size(starSize)
            )
        }

        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_half),
                contentDescription = "Half star",
                tint = activeColor,
                modifier = Modifier.size(starSize)
            )
        }

        repeat(maxStars - filledStars - if (halfStar) 1 else 0) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_outline),
                contentDescription = "Empty star",
                tint = inactiveColor,
                modifier = Modifier.size(starSize)
            )
        }
    }
}