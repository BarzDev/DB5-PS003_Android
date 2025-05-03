package com.example.android_db5_ps003.ui.components.umkm

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_db5_ps003.R


@Composable
fun UmkmECommerce(
    modifier: Modifier = Modifier,
    url: String
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_shopee),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    val msg = context.getString(R.string.umkm_linked, "Shopee")
                    Toast
                        .makeText(context, msg, Toast.LENGTH_SHORT)
                        .show()
                },
        )
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_tokopedia),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    val msg = context.getString(R.string.umkm_linked, "Tokopedia")
                    Toast
                        .makeText(context, msg, Toast.LENGTH_SHORT)
                        .show()
                },

            )
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_tiktokshop),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    val msg = context.getString(R.string.umkm_linked, "Tiktok Shop")
                    Toast
                        .makeText(context, msg, Toast.LENGTH_SHORT)
                        .show()
                },
        )
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_web),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                },
        )
    }
}

@Composable
fun EcommerceButton(
    modifier: Modifier = Modifier,
    icon: Painter,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(Color.White)
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Umkm_ECommercePreview() {
    UmkmECommerce(
        url = "www.google.com"
    )
}