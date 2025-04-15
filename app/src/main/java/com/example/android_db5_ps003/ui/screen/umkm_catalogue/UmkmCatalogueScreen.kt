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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.remote.response.UmkmItem
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.ViewModelFactory
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.EmptyComponent
import com.example.android_db5_ps003.ui.components.ErrorHandlerComponent
import com.example.android_db5_ps003.ui.components.LoadingComponent
import com.example.android_db5_ps003.ui.components.SearchBar
import com.example.android_db5_ps003.ui.components.umkm.Umkm_Card
import com.example.android_db5_ps003.util.formatRupiah

@Composable
fun UmkmCatalogueScreen(
    viewModel: UmkmCatalogueViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUmkmRepository())
    ),
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }
    val count by viewModel.count.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchUmkm()
    }

    when (val state = uiState) {
        UiState.Loading -> {
            LoadingComponent()
        }

        is UiState.Success -> {
            val filteredProducts = viewModel.searchProducts(query)

            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp)
            ) {
                SearchBar(query = query, onQueryChange = { query = it }, count = count)

                if (filteredProducts.isEmpty()) {
                    EmptyComponent(msg = stringResource(R.string.data_not_found))
                } else {
                    UmkmList(
                        umkms = filteredProducts,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail
                    )
                }


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
    navigateToDetail: (Int) -> Unit
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
                modifier = Modifier.clickable { navigateToDetail(umkm.id) }
            )
        }
    }
}
