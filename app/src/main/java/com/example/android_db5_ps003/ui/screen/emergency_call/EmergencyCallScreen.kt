package com.example.android_db5_ps003.ui.screen.emergency_call

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_db5_ps003.R
import com.example.android_db5_ps003.data.model.EmergencyCalls
import com.example.android_db5_ps003.di.Injection
import com.example.android_db5_ps003.ui.components.emergency_call.Emergency_Public_ItemLayout
import com.example.android_db5_ps003.ui.viewmodelfactory.EmergencyViewModelFactory

@Composable
fun EmergencyCallScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: EmergencyCallViewModel = viewModel(
        factory = EmergencyViewModelFactory(
            repository = Injection.provideEmergencyRepository()
        )
    )
) {
    val emergencyData = viewModel.getAllEmergencyData()
    EmergencyScreenContent(
        calls = emergencyData,
        modifier = modifier,
        context = context
    )
}

@Composable
fun EmergencyScreenContent(
    modifier: Modifier = Modifier,
    calls: List<EmergencyCalls>,
    context: Context
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
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
                        .clickable {
                            val serviceNumber = Uri.parse("tel:${data.number}")
                            val intent = Intent(Intent.ACTION_DIAL, serviceNumber)
                            context.startActivity(intent)
                        },
                    image = data.image,
                    emergencyName = data.name
                )
            }
        }
    }
}