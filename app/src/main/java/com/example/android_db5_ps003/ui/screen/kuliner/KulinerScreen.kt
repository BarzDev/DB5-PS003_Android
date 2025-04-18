package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.android_db5_ps003.data.remote.response.DataItem
import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import com.example.android_db5_ps003.ui.kuliner.KulinerItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import retrofit2.Response

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun KulinerScreen(
    modifier: Modifier = Modifier,
    viewModel: KulinerViewModel = remember { KulinerViewModel(ApiConfig.getApiService()) },
    onItemClick: (Int) -> Unit = {}
) {
    val kulinerList by viewModel.kulinerList.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val pagerState = rememberPagerState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Katalog Kuliner",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),

            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
                return@Column
            }

            if (errorMessage != null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = errorMessage ?: "Unknown error")
                }
                return@Column
            }

            // Featured Section
            Text(
                text = "Featured",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(kulinerList.take(5)) { kuliner ->
                    FeaturedKulinerItem(kuliner = kuliner, onItemClick = onItemClick)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Categories with TabLayout
            if (categories.isNotEmpty()) {
                TabLayout(categories = categories, pagerState = pagerState)

                HorizontalPager(
                    count = categories.size,
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    val category = categories[page]
                    val kulinerByCategory = viewModel.getKulinerByCategory(category)

                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(kulinerByCategory) { kuliner ->
                            KulinerItem(kuliner = kuliner, onItemClick = onItemClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FeaturedKulinerItem(
    kuliner: DataItem,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .width(200.dp)
            .clickable { kuliner.id?.let { onItemClick(it) } },
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = kuliner.img),
                contentDescription = kuliner.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = kuliner.name ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Rp${kuliner.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = kuliner.location ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(
    categories: List<String>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    // Calculate tab widths for indicator
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Float>()
        repeat(categories.size) {
            tabWidthStateList.add(0f)
        }
        tabWidthStateList
    }

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 16.dp,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { tabPositions ->
            val currentTabPosition = tabPositions[pagerState.currentPage]
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.BottomStart)
                    .offset(x = currentTabPosition.left)
                    .width(currentTabPosition.width)
                    .height(2.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
            )
        }
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.onGloballyPositioned { coordinates ->
                            tabWidths[index] = coordinates.size.width.toFloat()
                        }
                    )
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun KulinerScreenPreview() {
    val mockViewModel = KulinerViewModel(object : ApiService {
        override suspend fun getDataKuliner(): Response<KulinerResponse> {
            return Response.success(
                KulinerResponse(
                    status = 200,
                    message = "Success",
                    data = listOf(
                        DataItem(
                            id = 1,
                            name = "Nasi Goreng",
                            price = 25000,
                            location = "Jakarta",
                            category = "Main Course",
                            img = "",
                            description = "",
                            contact = "",
                            ratings = 4.5
                        )
                    )
                )
            )
        }
    })

    KulinerScreen(viewModel = mockViewModel)
}