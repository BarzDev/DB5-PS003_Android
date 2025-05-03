package com.example.android_db5_ps003.ui.components.umkm

import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.util.generatePhoneNumber

@Composable
fun UmkmDetail(
    name: String,
    price: String,
    image: String,
    owner: String,
    location: String,
    link: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val phone = generatePhoneNumber()
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(image)
            .size(200)
            .placeholder(R.drawable.ic_refresh_black)
            .error(R.drawable.ic_broken_image_black)
            .build()
    )

    Column(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 20.dp)
        ) {
            item {
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
            }

            item {
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
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    UmkmECommerce(url = link)
                }
            }

            item { Spacer(modifier = modifier.height(10.dp)) }

            item {
                Text(
                    modifier = modifier.padding(horizontal = 20.dp),
                    text = name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 20.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            item { Spacer(modifier = modifier.height(10.dp)) }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = stringResource(R.string.umkm_description_txt)
                )
            }

            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    text = description,
                    textAlign = TextAlign.Justify,
                    lineHeight = 20.sp,
                )
            }

            item {
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 20.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
            }

            item { Spacer(modifier = modifier.height(10.dp)) }

            item {
                Text(
                    text = stringResource(R.string.umkm_owner, owner),
                    modifier = modifier.padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )

                Text(
                    text = stringResource(R.string.umkm_location, location),
                    modifier = modifier.padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )

                Text(
                    text = stringResource(R.string.umkm_contact, phone),
                    modifier = modifier.padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                HorizontalDivider(
                    modifier = modifier.padding(horizontal = 20.dp, vertical = 5.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Button(
                onClick = {
                    val msg = "Berhasil membeli produk $name"
                    Toast
                        .makeText(context, msg, Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .semantics(mergeDescendants = true) {
                        contentDescription = "Order Button"
                    }
            ) {
                Text(text = stringResource(R.string.umkm_buy))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Umkm_DetailPreview() {
    UmkmDetail(
        name = "Product 1",
        price = "Rp. 5.000",
        image = "https://product1.jpg",
        owner = "fakhrul akbar",
        location = "Indonesia",
        link = "www.google.com",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris."
    )
}