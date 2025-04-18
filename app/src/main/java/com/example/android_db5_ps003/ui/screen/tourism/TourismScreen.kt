package com.example.android_db5_ps003.ui.screen.tourism

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.remote.response.Tourism
import com.example.android_db5_ps003.ui.ViewModelFactory
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.LoadingIndicator
import com.example.android_db5_ps003.ui.components.TourismItem

@Composable
fun TourismScreen(
    modifier: Modifier = Modifier,
    viewModel: TourismViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsStateWithLifecycle().value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllTourism()
                LoadingIndicator()
            }

            is UiState.Success -> {
                TourismContent(
                    modifier = modifier,
                    tourism = uiState.data,
                    navigateBack = navigateBack,
                    navigateToDetail = navigateToDetail
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
fun TourismContent(
    modifier: Modifier,
    tourism: List<Tourism>,
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tourism),
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
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding),
        ) {
            items(tourism, key = { it.id }) { tourism ->
                TourismItem(
                    image = tourism.img,
                    name = tourism.name,
                    rating = tourism.rating.toString(),
                    modifier = modifier.clickable(
                        onClick = { navigateToDetail(tourism.id) }
                    )
                )
            }
        }
    }
}