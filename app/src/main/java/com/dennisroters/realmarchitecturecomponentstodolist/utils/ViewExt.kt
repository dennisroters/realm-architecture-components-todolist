package com.dennisroters.realmarchitecturecomponentstodolist.utils

import androidx.core.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver


/**
 * VISIBILITY
 */
fun View?.show() { this?.visibility = View.VISIBLE }
fun View?.hide() { this?.visibility = View.GONE }

fun View?.showIf(condition: Boolean) = if (condition) show() else hide()
fun View?.hideIf(condition: Boolean) = if (condition) hide() else show()

var View?.visible: Boolean
    get(){
        return this?.visibility == View.VISIBLE
    }
    set(value) {
        if(value)
            this?.visibility = View.VISIBLE
        else
            this?.visibility = View.GONE
    }

var View?.gone: Boolean
    get(){
        return this?.visibility == View.GONE
    }
    set(value) {
        if(value)
            this?.visibility = View.GONE
        else
            this?.visibility = View.VISIBLE
    }

var View?.invisible: Boolean
    get(){
        return this?.visibility == View.INVISIBLE
    }
    set(value) {
        if(value)
            this?.visibility = View.INVISIBLE
        else
            this?.visibility = View.VISIBLE
    }

/**
 * CLICK
 */
inline fun <T : View?> T.onClick(crossinline func: T.() -> Unit?) {
    this?.setOnClickListener { func() }
}

inline fun <T : View?> T.onLongClick(crossinline func: T.() -> Unit) {
    this?.setOnLongClickListener { func(); true }
}

/**
 * PADDING
 */
fun View?.setPaddingStart(paddingStart: Int){
    if(this == null) return
    ViewCompat.setPaddingRelative(this,
            paddingStart,
            paddingTop,
            ViewCompat.getPaddingEnd(this),
            paddingBottom)
}

fun View?.setPaddingTop(paddingTop: Int) {
    if(this == null) return
    ViewCompat.setPaddingRelative(this,
            ViewCompat.getPaddingStart(this),
            paddingTop,
            ViewCompat.getPaddingEnd(this),
            paddingBottom)
}

fun View?.setPaddingEnd(paddingEnd: Int) {
    if(this == null) return
    ViewCompat.setPaddingRelative(this,
            ViewCompat.getPaddingStart(this),
            paddingTop,
            paddingEnd,
            paddingBottom)
}

fun View?.setPaddingBottom(paddingBottom: Int) {
    if (this == null) return
    ViewCompat.setPaddingRelative(this,
            ViewCompat.getPaddingStart(this),
            paddingTop,
            ViewCompat.getPaddingEnd(this),
            paddingBottom)
}

fun View?.setPaddingDp(left: Int, top: Int, right: Int, bottom: Int) {
    if (this == null) return
    setPadding(left.dp, top.dp, right.dp, bottom.dp)
}

/**
 * MARGIN
 */
fun View?.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    if (this != null && layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(left, top, right, bottom)
        requestLayout()
    }
}
fun View?.setMargins(margin: Int) {
    if (this != null && layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(margin, margin, margin, margin)
        requestLayout()
    }
}

fun View?.setMarginsDp(left: Int, top: Int, right: Int, bottom: Int) {
    if (this == null) return
    setMargins(left.dp, top.dp, right.dp, bottom.dp)
}
fun View?.setMarginsDp(margin: Int) {
    if (this == null) return
    setMargins(margin.dp)
}

/**
 * ViewGroup
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ViewGroup.setChildrenEnabled(enabled: Boolean) {

    isEnabled = enabled
    for (i in 0 until childCount) {
        val child = getChildAt(i)
        if (child is ViewGroup) {
            child.setChildrenEnabled(enabled)
        } else {
            child.isEnabled = enabled
        }
    }

}

/**
 * Measurement
 */
inline fun <T: View> T.afterMeasured(crossinline methodToCall: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                methodToCall()
            }
        }
    })
}
