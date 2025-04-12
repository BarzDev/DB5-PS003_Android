package com.example.android_db5_ps003.ui.screen.umkm_catalogue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UmkmCatalogueScreen(
    navigateToDetail :()-> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
//        Text(text = "UMKM SCREEN")
        Button(onClick = navigateToDetail) {
            Text(text = "go to detail")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UmkmCatalogueScreenPreview() {
    UmkmCatalogueScreen(navigateToDetail = {})
}