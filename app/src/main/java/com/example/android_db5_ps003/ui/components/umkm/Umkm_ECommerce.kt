package com.example.android_db5_ps003.ui.components.umkm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_db5_ps003.R


@Composable
fun Umkm_ECommerce(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_shopee),
            color = Color(0xFFFF5722),
            modifier = Modifier.height(24.dp),
            onClick = {}
        )
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_tokopedia),
            color = Color(0xFF03AC0E),
            modifier = Modifier.height(24.dp),
            onClick = {}
        )
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_tiktokshop),
            color = Color.Black,
            modifier = Modifier.height(24.dp),
            onClick = {}
        )
        EcommerceButton(
            icon = painterResource(id = R.drawable.img_web),
            color = Color.Black,
            modifier = Modifier.height(24.dp),
            onClick = {}
        )
    }
}

@Composable
fun EcommerceButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
//            .clip(RoundedCornerShape(10.dp))
//            .border(2.dp, color, RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
//            .padding(6.dp)
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
    Umkm_ECommerce()
}