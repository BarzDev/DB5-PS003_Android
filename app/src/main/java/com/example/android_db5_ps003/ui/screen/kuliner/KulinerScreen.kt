package com.example.android_db5_ps003.ui.screen.kuliner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.android_db5_ps003.data.remote.response.DataItem
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.kuliner.Item_column
import com.example.android_db5_ps003.ui.components.kuliner.Item_row
import com.example.android_db5_ps003.ui.viewmodelfactory.KulinerViewModelFactory
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.example.android_db5_ps003.ui.components.SearchBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun KulinerScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: KulinerViewModel = viewModel(
        factory = KulinerViewModelFactory(
            Injection.provideKulinerRepository(
                Injection.provideApiService()
            )
        )
    )
) {
    val statuses by viewModel.statuses.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()
    val pagerState = rememberPagerState()
    val uiState by viewModel.uiState.collectAsState()

    val kulinerData = remember { mutableStateOf<List<DataItem>>(emptyList()) }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            kulinerData.value = (uiState as UiState.Success).data
        }
    }

    Scaffold(
//        containerColor = Color.White,
        topBar = {
            Column {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { viewModel.updateSearchQuery(it) },
                    modifier = Modifier,
                    placeholder = "Cari Kuliner"
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
            val uiState by viewModel.uiState.collectAsState()

            when (uiState) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Success -> {
                    val data = (uiState as UiState.Success).data
                }
                is UiState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = (uiState as UiState.Error).errorMessage)
                    }
                }
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
                            Item_column(
                                kuliner = kuliner,
                                onItemClick = { id ->
                                    navController.navigate("kuliner_detail/$id")
                                }
                            )
                        }
                    }
                }
            } else {
                if (kulinerData.value.isNotEmpty()) {
                    Text(
                        text = "Recommended",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        modifier = Modifier
                            .padding(16.dp)
                    )

                    val recommendedKuliner by remember {
                        derivedStateOf {
                            kulinerData.value.filter { it.status == "Recommended" }
                        }
                    }

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(recommendedKuliner.take(5)) { kuliner ->
                            Item_row(
                                kuliner = kuliner,
                                onItemClick = { id ->
                                    navController.navigate("kuliner_detail/$id")
                                },
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
                            val kulinerByStatus = kulinerData.value.filter { it.status == status }

                            LazyColumn(
                                contentPadding = PaddingValues(0.dp, 10.dp),
                                verticalArrangement = Arrangement.Top,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(kulinerByStatus) { kuliner ->
                                    Item_column(
                                        kuliner = kuliner,
                                        onItemClick = { id ->
                                            navController.navigate("kuliner_detail/$id")
                                        }
                                    )
                                }
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


//@Preview(showBackground = true)
//@Composable
//fun KulinerScreenPreview() {
//    val mockViewModel = KulinerViewModel(object : ApiService {
//        override fun getDataKuliner(): Response<KulinerResponse> {
//            return Response.success(
//                KulinerResponse(
//                    status = 200,
//                    message = "Success",
//                    data = listOf(
//                        DataItem(
//                            id = 1,
//                            name = "Nasi",
//                            price = 25000,
//                            location = "Jakarta",
//                            category = "Main Course",
//                            img = "",
//                            description = "",
//                            contact = "",
//                            ratings = 4.5
//                        )
//                    )
//                )
//            )
//        }
//    })
//
//    KulinerScreen(viewModel = mockViewModel)
//}