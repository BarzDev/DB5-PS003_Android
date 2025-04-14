package com.example.android_db5_ps003.ui.components.umkm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.android_db5_ps003.R

@Composable
fun Umkm_Detail(
    name: String,
    price: String,
    image: String,
    owner: String,
    location: String,
    link: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(image)
            .placeholder(R.drawable.ic_refresh_black).error(R.drawable.ic_broken_image_black)
            .build()
    )

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box(
                modifier = modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = modifier
                        .height(250.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }

            Row(
                modifier = modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = price,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium

                )

                Umkm_ECommerce()

            }

            Spacer(modifier = modifier.height(10.dp))
            Text(
                modifier = modifier.padding(horizontal = 20.dp),
                text = name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold

            )

            HorizontalDivider(
                modifier = modifier.padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                lineHeight = 20.sp,
            )
            HorizontalDivider(
                modifier = modifier
                    .padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = stringResource(R.string.umkm_owner, owner),
                modifier = modifier.padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = modifier.height(3.dp))
            Text(
                text = stringResource(R.string.umkm_location, location),
                modifier = modifier.padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = modifier.height(3.dp))
            Text(
                text = stringResource(R.string.umkm_contact),
                modifier = modifier.padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium
            )


        }
    }


}

@Preview(showBackground = true)
@Composable
fun Umkm_DetailPreview() {
    Umkm_Detail(
        name = "Product 1",
        price = "Rp. 5.000",
        image = "https://product1.jpg",
        owner = "fakhrul akbar",
        location = "Indonesia",
        link = "https://product1.jpg",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris."
    )
}