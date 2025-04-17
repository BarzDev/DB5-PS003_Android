package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.model.EmergencyCalls
import com.example.android_db5_ps003.data.model.EmergencyCallsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EmergencyRepository {
    fun getEmergencyData(): List<EmergencyCalls> {
        return EmergencyCallsData.calls
    }
}