package com.example.android_db5_ps003.data.model

import com.example.android_db5_ps003.R

object EmergencyCallsData {
    val calls = listOf(
        EmergencyCalls(
            id = 1,
            name = "Polisi",
            number = 911,
            image = R.drawable.ic_store
        ),
        EmergencyCalls(
            id = 2,
            name = "Pemadam Kebakaran",
            number = 911,
            image = R.drawable.ic_store
        ),
        EmergencyCalls(
            id = 3,
            name = "Ambulans",
            number = 911,
            image = R.drawable.ic_store
        ),
        EmergencyCalls(
            id = 4,
            name = "Dishub",
            number = 911,
            image = R.drawable.ic_store
        ),
        EmergencyCalls(
            id = 5,
            name = "Basarnas",
            number = 911,
            image = R.drawable.ic_store
        )
    )
}