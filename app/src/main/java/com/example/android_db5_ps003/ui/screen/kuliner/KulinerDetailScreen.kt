package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.remote.response.DataItem
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KulinerDetailScreen(
    kulinerId: Int,
    viewModel: KulinerViewModel,
    onBackClick: () -> Unit
) {
    val kulinerList by viewModel.kulinerList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val kuliner = kulinerList.firstOrNull { it.id == kulinerId }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Detail Kuliner",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(48.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 4.dp
                        )
                    }
                }
                kuliner == null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Data not found",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
                else -> {
                    KulinerDetailContent(
                        kuliner = kuliner,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun KulinerDetailContent(
    kuliner: DataItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
            Image(
                painter = rememberAsyncImagePainter(model = kuliner.img),
                contentDescription = kuliner.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )

            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 4.dp,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(x = (-8).dp, y = (-8).dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Rating",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = when (val ratings = kuliner.ratings) {
                            is Double -> "%.1f".format(ratings)
                            is String -> ratings
                            else -> "0.0"
                        },
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = kuliner.name ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    color = Color.Black
                )

                Text(
                    text = "Rp. ${kuliner.price?.let {
                        NumberFormat.getNumberInstance(Locale.US).format(it)
                    }}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Top)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = kuliner.location ?: "",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_category),
                        contentDescription = "Category",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = kuliner.category ?: "",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color.Black
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Divider(
                color = Color.LightGray.copy(alpha = 0.3f),
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Text(
                text = "Description",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Black
            )
            Text(
                text = kuliner.description ?: "No description available",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Contact Information",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
                color = Color.Black,
            )

            Surface(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = kuliner.contact ?: "No contact information available",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KulinerDetailPreview() {
    val mockKuliner = DataItem(
        id = 1,
        name = "Nasi Goreng Special dengan Bumbu Rempah Tradisional",
        price = 35000,
        location = "Jl. Sudirman No. 123, Jakarta Pusat",
        category = "Main Course",
        img = "https://example.com/nasigoreng.jpg",
        description = "Nasi goreng spesial dengan campuran daging ayam, udang, telur, dan sayuran segar. Dibuat dengan bumbu rempah tradisional yang memberikan cita rasa autentik. Disajikan dengan kerupuk, acar, dan sambal spesial buatan sendiri. Cocok untuk sarapan, makan siang, maupun makan malam.",
        contact = "0812-3456-7890 / @nasigorengspecial",
        ratings = 4.5
    )

    MaterialTheme {
        Surface(color = Color.White) {
            KulinerDetailContent(
                kuliner = mockKuliner,
                modifier = Modifier.background(Color.White)
            )
        }
    }
}