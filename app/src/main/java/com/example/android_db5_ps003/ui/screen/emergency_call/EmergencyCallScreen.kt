package com.example.android_db5_ps003.ui.screen.emergency_call

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.ui.viewmodelfactory.EmergencyViewModelFactory
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.model.EmergencyCalls
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.components.Emergency_Public_ItemLayout
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme

@Composable
fun EmergencyCallScreen(
    modifier: Modifier = Modifier,
    viewModel: EmergencyCallViewModel = viewModel(
        factory = EmergencyViewModelFactory(
            repository = Injection.provideEmergencyRepository(LocalContext.current)
        )
    )
) {
    val emergencyData = viewModel.getAllEmergencyData()
    EmergencyScreenContent(
        calls = emergencyData,
        navigateToCall = {}
    )
}

@Composable
fun EmergencyScreenContent(
    modifier: Modifier = Modifier,
    calls: List<EmergencyCalls>,
    navigateToCall: (Long) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.menu_emergency),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(R.string.emergency_description),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 54.dp, start = 8.dp, end = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(calls, key = { it.id }) { data ->
                Emergency_Public_ItemLayout(
                    modifier = Modifier
                        .clickable { navigateToCall(data.number) },
                    image = data.image,
                    emergencyName = data.name
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmergencyCallScreenPreview() {
    Android_DB5PS003Theme {
        EmergencyScreenContent(
            calls = listOf(
                EmergencyCalls(
                    id = 1,
                    name = "Basarnas",
                    number = 911,
                    image = R.drawable.ic_catalogue
                ),
                EmergencyCalls(
                    id = 2,
                    name = "Basarnas",
                    number = 911,
                    image = R.drawable.ic_catalogue
                ),
                EmergencyCalls(
                    id = 3,
                    name = "Basarnas",
                    number = 911,
                    image = R.drawable.ic_catalogue
                ),
                EmergencyCalls(
                    id = 4,
                    name = "Basarnas",
                    number = 911,
                    image = R.drawable.ic_catalogue
                ),
                EmergencyCalls(
                    id = 5,
                    name = "Basarnas",
                    number = 911,
                    image = R.drawable.ic_catalogue
                ),
                EmergencyCalls(
                    id = 6,
                    name = "Basarnas",
                    number = 911,
                    image = R.drawable.ic_catalogue
                ),
            ),
            navigateToCall = {}
        )
    }
}