package com.example.android_db5_ps003.util

import android.icu.text.NumberFormat
import java.util.Locale


fun formatRupiah(amount: Int): String {
    val formatter = NumberFormat.getNumberInstance(Locale("in", "ID")).apply {
        maximumFractionDigits = 0
    }
    return "Rp. ${formatter.format(amount)}"
}
