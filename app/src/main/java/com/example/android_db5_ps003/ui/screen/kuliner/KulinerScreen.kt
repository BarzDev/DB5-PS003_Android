package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_db5_ps003.data.remote.response.DataItem
import com.example.android_db5_ps003.data.remote.response.KulinerResponse
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.data.remote.retrofit.ApiService
import com.example.android_db5_ps003.ui.components.kuliner.Item_column
import com.example.android_db5_ps003.ui.components.kuliner.Item_row
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
    val statuses by viewModel.statuses.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val pagerState = rememberPagerState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Katalog Kuliner",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle back */ }) {
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

                SearchBar(
                    query = searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                if (searchQuery.isNotEmpty()) {
                    Text(
                        text = "${searchResults.size} results",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 24.dp, bottom = 8.dp)
                    )
                }
            }
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

            if (searchQuery.isNotEmpty()) {
                if (searchResults.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No results found for \"$searchQuery\"",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.primary
                            ))
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(2.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(searchResults) { kuliner ->
                            Item_column(kuliner = kuliner, onItemClick = onItemClick)
                        }
                    }
                }
            } else {
                Text(
                    text = "Recommended",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                )

                val recommendedKuliner by remember { derivedStateOf { viewModel.getRecommendedKuliner() } }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(recommendedKuliner.take(5)) { kuliner ->
                        Item_row(
                            kuliner = kuliner,
                            onItemClick = onItemClick,
                            modifier = Modifier.width(200.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (statuses.isNotEmpty()) {
                    TabLayout(statuses = statuses, pagerState = pagerState)

                    HorizontalPager(
                        count = statuses.size,
                        state = pagerState,
                        modifier = Modifier.weight(1f)
                    ) { page ->
                        val status = statuses[page]
                        val kulinerByStatus = viewModel.getKulinerByStatus(status)

                        LazyColumn(
                            contentPadding = PaddingValues(0.dp, 10.dp),
                            verticalArrangement = Arrangement.Top,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(kulinerByStatus) { kuliner ->
                                Item_column(kuliner = kuliner, onItemClick = onItemClick)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(
    statuses: List<String>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Float>()
        repeat(statuses.size) {
            tabWidthStateList.add(0f)
        }
        tabWidthStateList
    }

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 16.dp,
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary,
        divider = {
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black.copy(alpha = 0.1f)
            )
        },
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
        statuses.forEachIndexed { index, status ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    Text(
                        text = status,
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


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        placeholder = {
            Text("Search Food & Beverages")
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        modifier = modifier
            .shadow(2.dp, MaterialTheme.shapes.medium)
            .background(Color.White, MaterialTheme.shapes.medium)
    )
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