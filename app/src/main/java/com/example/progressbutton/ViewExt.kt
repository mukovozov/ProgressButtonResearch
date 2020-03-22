package com.example.progressbutton

import android.content.res.TypedArray
import android.util.Log

inline fun TypedArray.autoRecycle(action: (TypedArray) -> Unit) {
    try {
        action.invoke(this)
    } catch (e: Exception) {
        Log.d("VIEW", e.toString())
    } finally {
        recycle()
    }
}
