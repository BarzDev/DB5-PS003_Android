package com.example.android_db5_ps003.ui.screen.tourism

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.data.remote.response.Tourism
import com.example.android_db5_ps003.ui.ViewModelFactory
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.TourismItem

@Composable
fun TourismScreen(
    modifier: Modifier = Modifier,
    viewModel: TourismViewModel = viewModel(
        factory = ViewModelFactory.getInstance()
    ),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsStateWithLifecycle().value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllTourism()
            }

            is UiState.Success -> {
                TourismContent(
                    modifier = modifier,
                    tourism = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {
                Toast.makeText(LocalContext.current, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun TourismContent(
    modifier: Modifier,
    tourism: List<Tourism>,
    navigateToDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(tourism, key = { it.id }) { tourism ->
            TourismItem(
                image = tourism.img,
                name = tourism.name,
                rating = tourism.rating.toString(),
                modifier = modifier.clickable {
                    navigateToDetail(tourism.id)
                }
            )
        }
    }
}