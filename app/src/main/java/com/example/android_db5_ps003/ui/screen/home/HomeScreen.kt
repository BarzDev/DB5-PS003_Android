package com.example.android_db5_ps003.ui.screen.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.model.BannerData
import com.example.android_db5_ps003.data.model.toBannerData
import com.example.android_db5_ps003.data.remote.response.NewsItem
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.di.NewsViewModelFactory
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.ItemsChevron
import com.example.android_db5_ps003.ui.components.NewsBanner
import com.example.android_db5_ps003.ui.components.NewsCardItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = NewsViewModelFactory(Injection.provideNewsRepository(LocalContext.current))
    ),
    navigateToNewsCatalogue: () -> Unit,
    navigateToNewsDetail: (Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(Unit) {
        if (uiState is UiState.Loading) {
            viewModel.getNewsDataForBanner()
        }
    }

    when (uiState) {
        is UiState.Loading -> {}

        is UiState.Error -> {}

        is UiState.Success -> {
            val newsData = (uiState as UiState.Success).data
            val bannerData = newsData
                .shuffled()
                .take(5)
                .map { it.toBannerData() }
            HomeContent(
                banners = bannerData,
                newsData = newsData.take(5),
                navigateToNewsCatalogue = navigateToNewsCatalogue,
                navigateToNewsDetail = navigateToNewsDetail,
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    banners: List<BannerData>,
    newsData: List<NewsItem>,
    navigateToNewsCatalogue: () -> Unit,
    navigateToNewsDetail: (Long) -> Unit,
) {
    LazyColumn {
        item {
            NewsBanner(
                banners = banners,
                navigateToNewsDetail = navigateToNewsDetail
            )
        }
        item {
            ItemsChevron(
                fieldName = stringResource(R.string.pelayanan_publik),
                modifier = Modifier.padding(16.dp),
                navigateToCatalogue = {}
            )
            LazyRow {
                // Item Pelayanan Publik
            }
        }

        item {
            ItemsChevron(
                fieldName = stringResource(R.string.berita_terkini),
                modifier = Modifier
                    .padding(16.dp),
                navigateToCatalogue = navigateToNewsCatalogue
            )
            LazyRow(
                contentPadding = PaddingValues(8.dp),
            ) {
                items(newsData, key = { it.id }) { list ->
                    NewsCardItem(
                        headlineText = list.title ?: "",
                        urlImg = list.urlToImage ?: "",
                        date = list.publishedAt ?: "",
                        navigateToNewsDetail = navigateToNewsDetail,
                        id = list.id.toLong()
                    )
                }
            }
        }
    }
}