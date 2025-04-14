package com.example.android_db5_ps003.ui.screen.umkm_detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_db5_ps003.ui.components.umkm.Umkm_Detail

@Composable
fun UmkmDetailScreen(
    modifier: Modifier = Modifier
) {
//    Box(
//        modifier = modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center,
//    ) {
//        Text(text = "detail umkm")
//    }
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