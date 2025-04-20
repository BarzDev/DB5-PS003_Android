package com.example.android_db5_ps003.util

fun generatePhoneNumber(): String {
    val first = listOf("0812", "0857", "0821", "0896").random()
    val middle = (1000..9999).random()
    return "$first-$middle-XXXX"
}
