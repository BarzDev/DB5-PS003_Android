package com.example.android_db5_ps003

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.android_db5_ps003.data.remote.retrofit.ApiConfig
import com.example.android_db5_ps003.ui.theme.Android_DB5PS003Theme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_DB5PS003Theme {
                SmartCityApp()
            }
        }
//    code ini nanti dihapus saat mulai development fitur
        fetchApiData()
    }

//    code ini nanti dihapus saat mulai development fitur
    private fun fetchApiData() {
        val apiService = ApiConfig.getApiService()

        apiService.getDataUmkm().enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                if (response.isSuccessful) {
                    Log.d("API_RESPONSE", "Success: ${response.body()}")
                } else {
                    Log.e("API_RESPONSE", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("API_RESPONSE", "Failure: ${t.message}")
            }
        })
    }
}
