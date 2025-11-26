package com.example.hydroagrosense.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun jsonStringToMap(json: String?): Map<String, Any> {
    val type = object : TypeToken<Map<String, Any>>() {}.type
    return Gson().fromJson(json, type)
}
