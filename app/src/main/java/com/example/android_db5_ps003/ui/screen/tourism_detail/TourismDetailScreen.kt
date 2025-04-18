package com.example.android_db5_ps003.ui.screen.tourism_detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.remote.response.Tourism
import com.example.android_db5_ps003.ui.ViewModelFactory
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.LoadingIndicator

@Composable
fun TourismDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TourismDetailViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    tourismId: Int,
    navigateBack: () -> Unit,
    onNavigateButtonClicked: (String) -> Unit
) {
    viewModel.uiState.collectAsStateWithLifecycle().value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getTourismDetail(tourismId)
                LoadingIndicator()
            }

            is UiState.Success -> {
                TourismDetailContent(
                    modifier = modifier,
                    tourism = uiState.data,
                    navigateBack = navigateBack,
                    onNavigateButtonClicked = onNavigateButtonClicked
                )
            }

            is UiState.Error -> {
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourismDetailContent(
    modifier: Modifier,
    tourism: Tourism,
    navigateBack: () -> Unit,
    onNavigateButtonClicked: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tourism_detail),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            AsyncImage(
                model = tourism.img,
                contentDescription = stringResource(R.string.tourism_image),
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.height(16.dp))
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tourism.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = stringResource(R.string.rating_star),
                        modifier = modifier.size(16.dp)
                    )
                    Spacer(modifier = modifier.width(4.dp))
                    Text(
                        text = tourism.rating.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
            Text(
                text = tourism.address,
                modifier = modifier
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Justify,
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(R.string.description),
                modifier = modifier.padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = tourism.description,
                modifier = modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Justify,
                color = Color.Black.copy(alpha = 0.8f)
            )
            Spacer(modifier = modifier.weight(1f))
            Button(
                onClick = {
                    onNavigateButtonClicked(tourism.link)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(R.string.navigate_here),
                    fontSize = 16.sp
                )
                Spacer(modifier = modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
    showSystemUi = true
)
@Composable
fun TourismDetailContentPreview() {
    TourismDetailContent(
        modifier = Modifier,
        tourism = Tourism(
            id = 1,
            name = "Contoh Wisata",
            description = "Ini adalah deskripsi dari contoh wisata. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            address = "Jalan Contoh No. 123",
            img = "https://via.placeholder.com/600x400",
            rating = 4.5,
            link = "https://via.placeholder.com/600x400"
        ),
        navigateBack = {  },
        onNavigateButtonClicked = {  }
    )
}