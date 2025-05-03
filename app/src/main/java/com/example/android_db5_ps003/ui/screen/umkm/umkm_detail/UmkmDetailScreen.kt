package com.example.android_db5_ps003.ui.screen.umkm.umkm_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.common.UiState
import com.example.android_db5_ps003.ui.components.ErrorHandlerComponent
import com.example.android_db5_ps003.ui.components.LoadingComponent
import com.example.android_db5_ps003.ui.components.umkm.UmkmDetail
import com.example.android_db5_ps003.ui.viewmodelfactory.UmkmViewModelFactory
import com.example.android_db5_ps003.util.formatRupiah

@Composable
fun UmkmDetailScreen(
    id: Int,
    viewModel: UmkmDetailViewModel = viewModel(
        factory = UmkmViewModelFactory(Injection.provideUmkmRepository())
    ),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getDetailUmkm(id)
    }

    when (val state = uiState) {
        UiState.Loading -> {
            LoadingComponent()
        }

        is UiState.Success -> {
            UmkmDetail(
                name = state.data.name,
                price = formatRupiah(state.data.price),
                image = state.data.img,
                owner = state.data.owner,
                location = state.data.address,
                link = state.data.link,
                description = state.data.description,
                modifier = modifier
            )
        }

        is UiState.Error -> {
            ErrorHandlerComponent(
                errorMessage = state.errorMessage,
                onRetry = { viewModel.getDetailUmkm(id) })
        }
    }
}