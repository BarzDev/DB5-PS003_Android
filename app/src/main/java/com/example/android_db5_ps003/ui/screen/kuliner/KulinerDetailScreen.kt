package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.android_db5_ps003.data.remote.response.DataItem
import com.example.android_db5_ps003.data.remote.retrofit.ApiService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KulinerDetailScreen(
    kulinerId: Int,
    viewModel: KulinerViewModel,
    onBackClick: () -> Unit
) {
    val kulinerList by viewModel.kulinerList.collectAsState()
    val kuliner = kulinerList.firstOrNull { it.id == kulinerId }

    Scaffold(
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
        if (kuliner == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Data not found")
            }
        } else {
            KulinerDetailContent(
                kuliner = kuliner,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
            )
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
        // Image Section
        Image(
            painter = rememberAsyncImagePainter(model = kuliner.img),
            contentDescription = kuliner.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

                    // Main Content
        Column(
                    modifier = Modifier
                        .padding(16.dp)
                    ) {
                // Title and Price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = kuliner.name ?: "",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Rp${kuliner.price}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Location
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = kuliner.location ?: "",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // Category
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = "Category",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = kuliner.category ?: "",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                // Divider
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color.LightGray
                )

                // Description
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = kuliner.description ?: "No description available",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Contact Information
                Text(
                    text = "Contact Information",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = kuliner.contact ?: "No contact information available",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Ratings
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ratings: ",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    when (val ratings = kuliner.ratings) {
                        is Double -> Text(
                            text = "$ratings/5.0",
                            style = MaterialTheme.typography.titleLarge
                        )
                        is String -> Text(
                            text = ratings,
                            style = MaterialTheme.typography.titleLarge
                        )
                        else -> Text(
                            text = "Not rated",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun KulinerDetailPreview() {
    val mockKuliner = DataItem(
        id = 1,
        name = "Nasi Goreng Special",
        price = 35000,
        location = "Jakarta Pusat",
        category = "Main Course",
        img = "https://example.com/nasigoreng.jpg",
        description = "Nasi goreng spesial dengan campuran daging ayam, udang, telur, dan sayuran segar. Disajikan dengan kerupuk dan acar.",
        contact = "08123456789 / @nasigorengspecial",
        ratings = 4.5
    )

    MaterialTheme {
        KulinerDetailContent(
            kuliner = mockKuliner,
            modifier = Modifier.background(Color.White)
        )
    }
}