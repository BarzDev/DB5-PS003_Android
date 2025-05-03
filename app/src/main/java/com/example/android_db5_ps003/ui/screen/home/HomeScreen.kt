package com.example.android_db5_ps003.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.model.BannerData
import com.example.android_db5_ps003.data.remote.response.NewsItem
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.ErrorHandlerComponent
import com.example.android_db5_ps003.ui.components.ItemsChevron
import com.example.android_db5_ps003.ui.components.LoadingComponent
import com.example.android_db5_ps003.ui.components.PublicServiceComponent
import com.example.android_db5_ps003.ui.components.news.NewsBanner
import com.example.android_db5_ps003.ui.components.news.NewsCardItem
import com.example.android_db5_ps003.ui.viewmodelfactory.NewsViewModelFactory

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
        is UiState.Loading -> {
            LoadingComponent()
        }

        is UiState.Error -> {
            ErrorHandlerComponent(
                errorMessage = (uiState as UiState.Error).errorMessage
            ) {
                viewModel.getNewsDataForBanner()
            }
        }

        is UiState.Success -> {
            val newsData = (uiState as UiState.Success).data
            val bannerData = viewModel.bannerData.collectAsState()
            HomeContent(
                banners = bannerData.value,
                newsData = newsData.take(5),
                navigateToNewsCatalogue = navigateToNewsCatalogue,
                navigateToNewsDetail = navigateToNewsDetail,
                modifier = modifier
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
    LazyColumn(modifier = modifier) {
        item {
            NewsBanner(
                banners = banners,
                navigateToNewsDetail = navigateToNewsDetail
            )
        }
        item {
            Text(
                text = stringResource(R.string.pelayanan_publik),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
            LazyRow {
                item {
                    PublicServiceComponent(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }

        item {
            ItemsChevron(
                fieldName = stringResource(R.string.berita_terkini),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigateToNewsCatalogue()
                    },
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