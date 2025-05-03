package com.example.android_db5_ps003.data.repository

import com.example.android_db5_ps003.data.model.EmergencyCalls
import com.example.android_db5_ps003.data.model.EmergencyCallsData

class EmergencyRepository {
    fun getEmergencyData(): List<EmergencyCalls> {
        return EmergencyCallsData.calls
    }
}