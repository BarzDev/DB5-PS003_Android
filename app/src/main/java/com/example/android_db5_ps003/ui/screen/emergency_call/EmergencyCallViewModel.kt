package com.example.android_db5_ps003.ui.screen.emergency_call

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_db5_ps003.data.model.EmergencyCalls
import com.example.android_db5_ps003.data.repository.EmergencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmergencyCallViewModel(private val repository: EmergencyRepository) : ViewModel() {
    private val _emergency : MutableStateFlow<List<EmergencyCalls>>? = null
    val emergency : StateFlow<List<EmergencyCalls>>? get() = _emergency

    fun getAllEmergencyData() : List<EmergencyCalls> {
        return repository.getEmergencyData()
    }
}