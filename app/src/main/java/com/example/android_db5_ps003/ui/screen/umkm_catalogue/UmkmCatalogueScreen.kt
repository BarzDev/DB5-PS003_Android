package com.example.android_db5_ps003.ui.screen.umkm_catalogue

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.data.remote.response.UmkmItem
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.ViewModelFactory
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.ErrorHandlerComponent
import com.example.android_db5_ps003.ui.components.LoadingComponent
import com.example.android_db5_ps003.ui.components.umkm.Umkm_Card
import com.example.android_db5_ps003.util.formatRupiah

@Composable
fun UmkmCatalogueScreen(
    viewModel: UmkmCatalogueViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUmkmRepository())
    ),
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(Unit) {
        viewModel.fetchUmkm()
    }

    when (val state = uiState) {
        UiState.Loading -> {
            LoadingComponent()
        }

        is UiState.Success -> {
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp)
            ) {
                UmkmList(umkms = state.data, modifier = modifier, navigateToDetail = navigateToDetail)
            }

        }

        is UiState.Error -> {
            ErrorHandlerComponent(
                errorMessage = state.errorMessage,
                onRetry = { viewModel.fetchUmkm() })
        }

    }
}

@Composable
fun UmkmList(
    umkms: List<UmkmItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(umkms, key = { it.id }) { umkm ->
            Umkm_Card(
                title = umkm.name,
                image = umkm.img,
                price = formatRupiah(umkm.price),
                owner = umkm.owner,
                modifier = Modifier.clickable { navigateToDetail() }
            )
        }
    }
}