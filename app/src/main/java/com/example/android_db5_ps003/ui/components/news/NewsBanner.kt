package com.example.android_db5_ps003.ui.components.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.model.BannerData

@Composable
fun NewsBanner(
    modifier: Modifier = Modifier,
    banners: List<BannerData>,
    navigateToNewsDetail : (Long) -> Unit
) {
    val pagerState = rememberPagerState { banners.size }

    Box(
        modifier = modifier
            .padding(16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            Box(
                modifier = Modifier.clickable { navigateToNewsDetail(banners[page].id.toLong()) }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(banners[page].urlImg)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_refresh_black)
                        .build(),
                    contentDescription = "Gambar Banner Berita",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(140.dp)
                )
                Text(
                    text = banners[page].headline,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(y = -20.dp)
                        .padding(horizontal = 8.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            repeat(banners.size) { index ->
                val color = if (pagerState.currentPage == index) Color.DarkGray else Color.LightGray
                Icon(
                    painter = painterResource(R.drawable.ic_dot),
                    tint = color,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 8.dp)
                )
            }
        }
    }
}
