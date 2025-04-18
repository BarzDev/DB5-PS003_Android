package com.example.android_db5_ps003.ui.kuliner

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.android_db5_ps003.data.remote.response.DataItem

@Composable
fun KulinerItem(
    kuliner: DataItem,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .clickable { kuliner.id?.let { onItemClick(it) } },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = kuliner.img),
                contentDescription = kuliner.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = kuliner.name ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Rp${kuliner.price}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}