package com.example.android_db5_ps003.ui.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.model.BannerData
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

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
                        .build(),
                    contentDescription = "Gambar Banner Berita",
                    contentScale = ContentScale.FillWidth,
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

/*
@Preview(showBackground = true)
@Composable
fun NewsBannerPreview() {
    Android_DB5PS003Theme {
        NewsBanner(
            banners = listOf(
                BannerData(
                    "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhZELNL5kuc2uWILtOG6XqCMU1SVxKwLdTz2eTUqXk1FLTbDA5NyzYfcqmW4smMMaFZRRzqVH10lyk5ExRKDVqTLPMdQ-kL38XS9Mo9xOVaq843pRcbvvbR6ftcIFsazJOKCZVfsvr9KbaVc7Sr28nd4dxZNXW63o9FM72cnWoP6C1_4DXt-RFDGhp2F95v/s1600/WhatsApp%20Image%202025-03-29%20at%2016.09.26.jpeg",
                    "Kelurahan Selang Back-to-Back Champions di Turnamen Futsal Fort Ramadhan 2025"
                ),
                BannerData(
                    "https://plus.unsplash.com/premium_photo-1664474619075-644dd191935f?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8aW1hZ2V8ZW58MHx8MHx8fDA%3D",
                    "YOIII"
                ),
                BannerData(
                    "https://gratisography.com/wp-content/uploads/2024/11/gratisography-augmented-reality-800x525.jpg",
                    "WKOWKWOK"
                ),
                BannerData(
                    "https://www.bigfootdigital.co.uk/wp-content/uploads/2020/07/image-optimisation-scaled.jpg",
                    "BEJIRR"
                ),
                BannerData(
                    "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg",
                    "GEMING"
                )
            )
        )
    }
}*/
