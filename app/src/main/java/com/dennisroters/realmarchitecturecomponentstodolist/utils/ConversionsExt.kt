package com.dennisroters.realmarchitecturecomponentstodolist.utils

import android.content.res.Resources

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Float.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Float.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.abs: Float
    get() {
        return Math.abs(this)
    }

val Float.inverted: Float
    get() {
        return this * -1
    }